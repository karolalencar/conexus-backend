package com.conexus.api.mappers;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Payment;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Services;
import com.conexus.api.dto.ServiceBetweenDto;
import com.conexus.api.dto.ServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(source = "service.client.id", target = "clientId")
    @Mapping(source = "service.professional.id", target = "professionalId")
    @Mapping(source = "service.payment.id", target = "paymentId")
    ServiceDto serviceToServiceDto(Services service);

    @Mapping(source = "serviceDto.clientId", target = "client")
    @Mapping(source = "serviceDto.professionalId", target = "professional")
    @Mapping(source = "serviceDto.paymentId", target = "payment")
    Services serviceDtoToService(ServiceDto serviceDto);

    Services serviceDtoToEntity(ServiceBetweenDto serviceBetweenDto);

    @Mapping(source = "clientId", target = "id")
    Client toClient(Long clientId);

    @Mapping(source = "professionalId", target = "id")
    Professional toProfessional(Long professionalId);

    @Mapping(source = "paymentId", target = "id")
    Payment toPayment(Long paymentId);
}
