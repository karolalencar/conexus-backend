package com.conexus.api.controllers;

import com.conexus.api.domain.Rating;
import com.conexus.api.dto.RatingDto;
import com.conexus.api.mappers.RatingMapper;
import com.conexus.api.services.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RestController
@Tag(name = "Avaliações")
@RequestMapping("/ratings")
public class RatingController {

    private final RatingMapper ratingMapper;
    private final RatingService ratingService;

    public RatingController(RatingMapper ratingMapper, RatingService ratingService) {
        this.ratingMapper = ratingMapper;
        this.ratingService = ratingService;
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
}
