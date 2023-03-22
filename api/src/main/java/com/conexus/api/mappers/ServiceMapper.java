package com.conexus.api.mappers;

import com.conexus.api.domain.Service;
import com.conexus.api.dto.ServiceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDto serviceToServiceDto(Service service);

    Service serviceDtoToService(ServiceDto serviceDto);
}
