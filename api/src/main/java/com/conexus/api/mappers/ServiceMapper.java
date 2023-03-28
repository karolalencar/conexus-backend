package com.conexus.api.mappers;

import com.conexus.api.domain.Services;
import com.conexus.api.dto.ServiceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDto serviceToServiceDto(Services service);

    Services serviceDtoToService(ServiceDto serviceDto);
}
