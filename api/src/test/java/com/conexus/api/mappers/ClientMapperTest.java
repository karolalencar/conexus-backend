package com.conexus.api.mappers;

import com.conexus.api.domain.Client;
import com.conexus.api.dto.ClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientMapperTest {

    public static final Long ID = 1L;
    public static final String NAME = "TestName";
    public static final String EMAIL = "test@email.com";
    public static final String CPF = "0123456789";
    public static final String PASSWORD = "password";

    @InjectMocks
    private ClientMapperImpl clientMapper;

    private Client client;

    private ClientDto clientDto;

    @BeforeEach
    void setUp() {

        client = new Client(ID, NAME, EMAIL, CPF, PASSWORD);
        clientDto = new ClientDto(ID, NAME, EMAIL, CPF, PASSWORD);
    }

    @Test
    void testClientToClientDto() {

        ClientDto resultClientDto = clientMapper.clientToClientDto(client);

        assertEquals(ID, resultClientDto.getId());
        assertEquals(NAME, resultClientDto.getName());
        assertEquals(EMAIL, resultClientDto.getEmail());
        assertEquals(CPF, resultClientDto.getCpf());
        assertEquals(PASSWORD, resultClientDto.getPassword());

    }

    @Test
    void testClientDtoToClient() {

        Client resultClient = clientMapper.clientDtoToClient(clientDto);

        assertEquals(ID, resultClient.getId());
        assertEquals(NAME, resultClient.getName());
        assertEquals(EMAIL, resultClient.getEmail());
        assertEquals(CPF, resultClient.getCpf());
        assertEquals(PASSWORD, resultClient.getPassword());
    }
}