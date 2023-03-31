package com.conexus.api.controllers;

import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.dto.RatingDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.mappers.RatingMapper;
import com.conexus.api.services.ProfessionalService;
import com.conexus.api.services.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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


    public ProfessionalController(ProfessionalMapper professionalMapper, RatingMapper ratingMapper, ProfessionalService professionalService, RatingService ratingService) {
        this.professionalMapper = professionalMapper;
        this.ratingMapper = ratingMapper;
        this.professionalService = professionalService;
        this.ratingService = ratingService;
    }
    @Operation(summary = "Retorna a lista de todos os profissionais")
    @GetMapping("")
    public List<ProfessionalDto> getProfessionals() {
        List<ProfessionalDto> professionals = professionalService.findAll()
                .stream()
                .map(professionalMapper::professionalToProfessionalDto)
                .collect(Collectors.toList());
        return professionals;
    }

    @Operation(summary = "Retorna um profissional pelo id")
    @GetMapping("/{id}")
    public ProfessionalDto getProfessional(@PathVariable Long id) {
        Professional professional = professionalService.findById(id);
        ProfessionalDto professionalDto = professionalMapper.professionalToProfessionalDto(professional);
        return professionalDto;
    }

    @Operation(summary = "Retorna um profissional pela categoria")
    @GetMapping(params = "category")
    public List<ProfessionalDto> getAllProfessionalsByCategory(@RequestParam String category) {
        List<ProfessionalDto> professionals = professionalService.findAllByCategory(category)
                .stream()
                .map(professionalMapper::professionalToProfessionalDto)
                .collect(Collectors.toList());
        return professionals;
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
    public ResponseEntity<?> createProfessional(@RequestBody ProfessionalDto professionalDto) {
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
