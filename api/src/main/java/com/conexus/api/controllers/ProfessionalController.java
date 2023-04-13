package com.conexus.api.controllers;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Services;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.dto.RatingDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.mappers.RatingMapper;
import com.conexus.api.services.ProfessionalService;
import com.conexus.api.services.RatingService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Profissionais")
@RequestMapping("professionals")
public class ProfessionalController {

    private final ProfessionalMapper professionalMapper;
    private final RatingMapper ratingMapper;
    private final ProfessionalService professionalService;
    private final RatingService ratingService;
    private final ServiceService serviceService;


    public ProfessionalController(ProfessionalMapper professionalMapper, RatingMapper ratingMapper, ProfessionalService professionalService, RatingService ratingService, ServiceService serviceService) {
        this.professionalMapper = professionalMapper;
        this.ratingMapper = ratingMapper;
        this.professionalService = professionalService;
        this.ratingService = ratingService;
        this.serviceService = serviceService;
    }

    @Operation(summary = "Retorna um profissional pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDto> getProfessional(@PathVariable Long id) {
        Professional professional = professionalService.findById(id);
        ProfessionalDto professionalDto = professionalMapper.professionalToProfessionalDto(professional);
        return ResponseEntity.ok().body(professionalDto);
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

    @Operation(summary = "")
    @GetMapping()
    public ResponseEntity<Page<ProfessionalDto>> searchAllProfessionalsByCategory(
            @RequestParam(name = "category") String category,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {

            Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
            Page<ProfessionalDto> professionals = professionalService.findAllByCategory(category, pageable);
            return ResponseEntity.ok(professionals);
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


    @Operation(summary = "Cria um novo profissional")
    @PostMapping("")
    public ResponseEntity<?> createProfessional(@Valid @RequestBody ProfessionalDto professionalDto) {
        try {
            Professional professional = professionalMapper.professionalDtoToProfessional(professionalDto);
            Professional newProfessional = professionalService.save(professional);
            return ResponseEntity.ok(newProfessional);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating professional: " + e.getMessage());
        }
    }

    @Operation(summary = "Retorna todas as avaliações de um profissional pelo id")
    @GetMapping("/{id}/ratings")
    public List<RatingDto> getRatingsByProfessional(@PathVariable Long id) {
        List<RatingDto> ratings = ratingService.findAllByProfessionalId(id)
                .stream()
                .map(ratingMapper::ratingToRatingDto)
                .collect(Collectors.toList());
        return ratings;
    }

    @Operation(summary = "Deleta um profissinal pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessional(@PathVariable Long id) {

        Professional professional = professionalService.findById(id);
        if (professional == null) {
            return ResponseEntity.notFound().build();
        }

        List<Services> servicesByProfessionalId = serviceService.findAllByProfessionalId(id);
        for(Services service : servicesByProfessionalId){
            service.setProfessional(null);
        }

        professionalService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /*@Operation(summary = "Atualiza um profissional pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalDto> updateProfessional(@PathVariable Long id, @RequestBody ProfessionalDto professionalDto) {
        Professional professional = professionalService.findById(id);
        if (professional == null) {
            return ResponseEntity.notFound().build();
        }

        Professional updatedProfessional = professionalMapper.
    }*/

}
