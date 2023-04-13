package com.conexus.api.controllers;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.dto.RatingDto;
import com.conexus.api.mappers.RatingMapper;
import com.conexus.api.services.ProfessionalService;
import com.conexus.api.services.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RestController
@Tag(name = "Avaliações")
@RequestMapping("/ratings")
public class RatingController {

    private final RatingMapper ratingMapper;
    private final RatingService ratingService;
    private final ProfessionalService professionalService;

    public RatingController(RatingMapper ratingMapper, RatingService ratingService, ProfessionalService professionalService) {
        this.ratingMapper = ratingMapper;
        this.ratingService = ratingService;
        this.professionalService = professionalService;
    }

    @Operation(summary = "Retorna a lista de todas as avaliações")
    @GetMapping("")
    public List<RatingDto> getRatings() {
        List<RatingDto> ratings = ratingService.findAll()
                .stream()
                .map(ratingMapper::ratingToRatingDto)
                .collect(Collectors.toList());
        return ratings;
    }

    @Operation(summary = "Cria uma nova avaliação")
    @PostMapping("")
    public ResponseEntity<?> createRating(@Valid @RequestBody RatingDto ratingDto) {

        try {

            Rating rating = ratingMapper.ratingDtoToRating(ratingDto);
            Rating newRating = ratingService.save(rating);
            return ResponseEntity.ok(ratingMapper.ratingToRatingDto(newRating));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating rating: " + e.getMessage());
        }
    }

    @Operation(summary = "Deleta uma avaliação pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {

        Rating rating = ratingService.findById(id);
        if (rating == null) {
            return ResponseEntity.notFound().build();
        }

        ratingService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
