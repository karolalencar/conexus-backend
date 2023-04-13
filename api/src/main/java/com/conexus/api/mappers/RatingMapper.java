package com.conexus.api.mappers;

import com.conexus.api.domain.Rating;
import com.conexus.api.dto.RatingDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RatingMapper {


    @Mapping(target = "professional_id", ignore = true)
    RatingDto ratingToRatingDto(Rating rating);

    @Mapping(target = "professional", ignore = true)
    Rating ratingDtoToRating(RatingDto ratingDto);
}
