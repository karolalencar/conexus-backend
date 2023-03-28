package com.conexus.api.mappers;

import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ProfessionalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfessionalMapper {

    ProfessionalMapper INSTANCE = Mappers.getMapper(ProfessionalMapper.class);

    ProfessionalDto professionalToProfessionalDto(Professional professional);

    Professional professionalDtoToProfessional(ProfessionalDto professionalDto);
}
