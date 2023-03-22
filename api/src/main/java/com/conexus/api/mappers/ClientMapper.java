package com.conexus.api.mappers;

import com.conexus.api.domain.Client;
import com.conexus.api.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto clientToClientDto(Client client);

    Client clientDtoToClient(ClientDto clientDto);
}
