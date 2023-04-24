package com.conexus.api.mappers;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.dto.RatingBetweenDto;
import com.conexus.api.dto.RatingDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RatingMapper {


    @Mapping(source = "rating.professional.id", target = "professionalId")
    @Mapping(source = "rating.client.id", target = "clientId")
    RatingDto ratingToRatingDto(Rating rating);

    @Mapping(source = "ratingDto.professionalId", target = "professional")
    @Mapping(source = "ratingDto.clientId", target = "client")
    Rating ratingDtoToRating(RatingDto ratingDto);

    Rating ratingDtoToEntity(RatingBetweenDto ratingBetweenDto);

    @Mapping(source = "professionalId", target = "id")
    Professional toProfessional(Long professionalId);

    @Mapping(source = "clientId", target = "id")
    Client toClient(Long clientId);
}
