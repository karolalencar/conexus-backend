package com.conexus.api.mappers;

import com.conexus.api.domain.Rating;
import com.conexus.api.dto.RatingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    RatingDto ratingToRatingDto(Rating rating);

    Rating ratingDtoToRating(RatingDto ratingDto);
}
