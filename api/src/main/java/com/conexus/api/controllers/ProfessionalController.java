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

    @PostMapping
    public ResponseEntity<Professional> createProfessional(@RequestBody ProfessionalDto professionalDto) {
        Professional professional = professionalMapper.professionalDtoToProfessional(professionalDto);
        return ResponseEntity.ok(professional);
    }

    @GetMapping("/{id}/ratings")
    public List<RatingDto> getRatingsByProfessional(@PathVariable Long id) {
        List<RatingDto> ratings = ratingService.findAllByProfessionalId(id)
                .stream()
                .map(ratingMapper::ratingToRatingDto)
                .collect(Collectors.toList());
        return ratings;
    }

}
