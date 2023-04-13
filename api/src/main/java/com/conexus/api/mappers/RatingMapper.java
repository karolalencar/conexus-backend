package com.conexus.api.mappers;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.dto.RatingBetweenDto;
import com.conexus.api.dto.RatingDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RatingMapper {


    @Mapping(source = "rating.professional.id", target = "professionalId")
    RatingDto ratingToRatingDto(Rating rating);

    @Mapping(source = "ratingDto.professionalId", target = "professional")
    Rating ratingDtoToRating(RatingDto ratingDto);

    Rating ratingDtoToEntity(RatingBetweenDto ratingBetweenDto);

    @Mapping(source = "professionalId", target = "id")
    Professional toProfessional(Long professionalId);
}
