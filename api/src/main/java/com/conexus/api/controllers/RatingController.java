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
import org.springframework.validation.BindingResult;
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

    @Operation(summary = "Cria uma nova avaliação")
    @PostMapping("")
    public ResponseEntity<?> createRating(@Valid @RequestBody RatingDto ratingDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Rating rating = ratingMapper.ratingDtoToRating(ratingDto);
        Rating newRating = ratingService.save(rating);
        return new ResponseEntity<>(ratingMapper.ratingToRatingDto(newRating), HttpStatus.CREATED);
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

    @Operation(summary = "Atualiza uma avaliação pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRating(@PathVariable Long id, @Valid @RequestBody RatingDto ratingDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Rating rating = ratingService.findById(id);
        if (rating == null) {
            return new ResponseEntity<>("Rating not found", HttpStatus.NOT_FOUND);
        }

        rating = ratingMapper.ratingDtoToRating(ratingDto);
        Rating updatedRating = ratingService.updateByRatingId(id, rating);
        return new ResponseEntity<>(ratingMapper.ratingToRatingDto(updatedRating), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta uma avaliação pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {

        Rating rating = ratingService.findById(id);
        if (rating == null) {
            return new ResponseEntity<>("Rating not found", HttpStatus.NOT_FOUND);
        }

        ratingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
