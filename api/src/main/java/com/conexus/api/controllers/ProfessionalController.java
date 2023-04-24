package com.conexus.api.controllers;

import com.conexus.api.config.PasswordHasher;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Schedule;
import com.conexus.api.domain.Services;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.dto.RatingDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.mappers.RatingMapper;
import com.conexus.api.repositories.ScheduleRepository;
import com.conexus.api.services.ProfessionalService;
import com.conexus.api.services.RatingService;
import com.conexus.api.services.ScheduleService;
import com.conexus.api.services.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;

@RestController
@Tag(name = "Profissionais")
@RequestMapping("professionals")
public class ProfessionalController {

    private final ProfessionalMapper professionalMapper;
    private final RatingMapper ratingMapper;
    private final ProfessionalService professionalService;
    private final RatingService ratingService;
    private final ServiceService serviceService;
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    public ProfessionalController(ProfessionalMapper professionalMapper, RatingMapper ratingMapper, ProfessionalService professionalService, RatingService ratingService, ServiceService serviceService, ScheduleService scheduleService, ScheduleRepository scheduleRepository) {
        this.professionalMapper = professionalMapper;
        this.ratingMapper = ratingMapper;
        this.professionalService = professionalService;
        this.ratingService = ratingService;
        this.serviceService = serviceService;
        this.scheduleService = scheduleService;
        this.scheduleRepository = scheduleRepository;
    }

    @Operation(summary = "Cria um novo profissional")
    @PostMapping("")
    public ResponseEntity<?> createProfessional(@Valid @RequestBody ProfessionalDto professionalDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        if (!professionalService.findByEmail(professionalDto.getEmail()).isEmpty()) {
            return new ResponseEntity<>("E-mail already registered", HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = PasswordHasher.hashPassword(professionalDto.getPassword());
        professionalDto.setPassword(hashedPassword);

        Professional professional = professionalMapper.professionalDtoToProfessional(professionalDto);
        professionalService.save(professional);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(professional.getId()).toUri();
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna a lista de todos os profissionais")
    @GetMapping
    public ResponseEntity<List<ProfessionalDto>> getProfessionals() {
        List<ProfessionalDto> professionals = professionalService.findAll()
                .stream()
                .map(professionalMapper::professionalToProfessionalDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(professionals, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um profissional pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDto> getProfessional(@PathVariable Long id) {

        Professional professional = professionalService.findById(id);
        ProfessionalDto professionalDto = professionalMapper.professionalToProfessionalDto(professional);
        return new ResponseEntity<>(professionalDto, HttpStatus.OK);
    }

    /*@Operation(summary = "Retorna todos os profissionais de uma categoria")
    @GetMapping(params = "category")
    public List<ProfessionalDto> getAllProfessionalsByCategory(@RequestParam String category) {
        List<ProfessionalDto> professionals = professionalService.findAllByCategory(category)
                .stream()
                .map(professionalMapper::professionalToProfessionalDto)
                .collect(Collectors.toList());
        return professionals;
    }*/

    @Operation(summary = "Retorna uma página de profissionais por categoria")
    @GetMapping("/categories")
    public ResponseEntity<?> searchAllProfessionalsByCategory(
            @RequestParam(name = "category") String category,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {

            Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
            Page<ProfessionalDto> professionals = professionalService.findAllByCategory(category, pageable);
            return ResponseEntity.ok(professionals.getContent());
    }

    @Operation(summary = "Retorna um profissional pela descrição")
    @GetMapping(params = "description")
    public List<ProfessionalDto> getAllProfessionalsByDescription(@RequestParam String description) {
        List<ProfessionalDto> professionals = professionalService.findAllByDescription(description)
                .stream()
                .map(professionalMapper::professionalToProfessionalDto)
                .collect(Collectors.toList());
        return professionals;
    }

    @Operation(summary = "Retorna todas as avaliações de um profissional pelo id")
    @GetMapping("/{id}/ratings")
    public ResponseEntity<?> getRatingsByProfessional(@PathVariable Long id) {
        try {
            List<RatingDto> ratings = ratingService.findAllByProfessionalId(id)
                    .stream()
                    .map(ratingMapper::ratingToRatingDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ratings);
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting the professionals's rating: " + e.getMessage());
        }
    }

    @Operation(summary = "Atualiza um profissional pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfessional(@PathVariable Long id, @Valid @RequestBody ProfessionalDto professionalDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Professional professional = professionalService.findById(id);
        if (professional == null) {
            return new ResponseEntity<>("Professional not found", HttpStatus.NOT_FOUND);
        }

        String hashedPassword = PasswordHasher.hashPassword(professionalDto.getPassword());
        professionalDto.setPassword(hashedPassword);

        professional = professionalMapper.professionalDtoToProfessional(professionalDto);
        Professional updatedProfessional = professionalService.updateByProfessionalId(id, professional);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("").buildAndExpand(updatedProfessional.getId()).toUri();
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um profissional pelo id")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProfessionalPatch(@PathVariable Long id, @RequestBody ProfessionalDto professionalDto) {

        Professional professional = professionalService.findById(id);
        if (professional == null) {
            return new ResponseEntity<>("Professional not found", HttpStatus.NOT_FOUND);
        }

        Professional updatedProfessional = professionalMapper.professionalDtoToProfessional(professionalDto);
        updatedProfessional = professionalService.updateByProfessionalIdPatch(professional, updatedProfessional);
        return new ResponseEntity<>( professionalMapper.professionalToProfessionalDto(updatedProfessional),HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta um profissinal pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessional(@PathVariable Long id) {

        Professional professional = professionalService.findById(id);
        if (professional == null) {
            return new ResponseEntity<>("Professional not found", HttpStatus.NOT_FOUND);
        }

        List<Services> servicesByProfessionalId = serviceService.findAllByProfessionalId(id);
        for(Services service : servicesByProfessionalId){
            service.setProfessional(null);
        }
        System.out.println(scheduleService.findAllByProfessionalId(id));
        List<Schedule> schedulesByProfessionalId = scheduleService.findAllByProfessionalId(id);
        for (Schedule schedule : schedulesByProfessionalId) {
            System.out.println(schedule);
            scheduleRepository.delete(schedule);
            System.out.println(schedule);
        }
        System.out.println(scheduleService.findAllByProfessionalId(id));

        professionalService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
