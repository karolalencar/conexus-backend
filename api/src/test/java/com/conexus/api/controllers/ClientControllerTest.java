package com.conexus.api.controllers;

import com.conexus.api.domain.*;
import com.conexus.api.dto.ClientDto;
import com.conexus.api.mappers.ClientMapper;
import com.conexus.api.repositories.ClientRepository;
import com.conexus.api.services.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientControllerTest {

    public static final Long ID = 1L;
    public static final String NAME = "TestName";
    public static final String EMAIL = "test@email.com";
    public static final String CPF = "0123456789";
    public static final String PASSWORD = "password";

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientServiceImpl clientService;

    @Mock
    private ClientMapper clientMapper;

    private ClientRepository clientRepository;

    @Mock
    private BindingResult bindingResult;

    private Client client;

    private ClientDto clientDto;

    @BeforeEach
    void setUp() {

        client = new Client(ID, NAME, EMAIL, CPF, PASSWORD);
        clientDto = new ClientDto(ID, NAME, EMAIL, CPF, PASSWORD);
    }

    @Test
    void testCreateClientSuccess() {

        when(clientMapper.clientDtoToClient(any(ClientDto.class))).thenReturn(client);
        when(clientService.save(any(Client.class))).thenReturn(client);

        ResponseEntity<?> responseEntity = clientController.createClient(clientDto, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testGetClientsSuccess() {

        when(clientService.findAll()).thenReturn(Set.of(client));

        ResponseEntity<List<ClientDto>> responseEntity = clientController.getClients();

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(ResponseEntity.class, responseEntity.getClass());
        assertEquals(ArrayList.class, responseEntity.getBody().getClass());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    void testGetClientSuccess() {

        when(clientService.findById(ID)).thenReturn(client);
        when(clientMapper.clientToClientDto(client)).thenReturn(clientDto);

        ResponseEntity<ClientDto> responseEntity = clientController.getClient(ID);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(ResponseEntity.class, responseEntity.getClass());
        assertEquals(ClientDto.class, responseEntity.getBody().getClass());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(ID, responseEntity.getBody().getId());
        assertEquals(NAME, responseEntity.getBody().getName());
        assertEquals(EMAIL, responseEntity.getBody().getEmail());
        assertEquals(CPF, responseEntity.getBody().getCpf());
        assertEquals(PASSWORD, responseEntity.getBody().getPassword());
    }

    @Test
    void testUpdateClientSuccess() {

        when(clientService.findById(ID)).thenReturn(client);
        when(clientMapper.clientDtoToClient(clientDto)).thenReturn(client);

        ResponseEntity<?> responseEntity = clientController.updateClient(ID, clientDto, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void updateClientPatch() {
    }

    @Test
    void testDeleteClientSuccess() {

        when(clientService.findById(ID)).thenReturn(client);

        ResponseEntity<?> responseEntity = clientController.deleteClient(ID);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(clientService, times(1)).deleteById(ID);
    }
}