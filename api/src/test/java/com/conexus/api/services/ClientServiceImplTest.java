package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "TestName";
    public static final String EMAIL = "test@email.com";
    public static final String CPF = "0123456789";
    public static final String PASSWORD = "password";

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    void setUp() {

        client = new Client(ID, NAME, EMAIL, CPF, PASSWORD);
    }

    @Test
    void testFindAll() {

        when(clientRepository.findAll()).thenReturn(List.of(client));

        Set<Client> response = clientService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testFindById() {

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        Client response = clientService.findById(ID);

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void testSave() {

        when(clientRepository.save(any())).thenReturn(client);

        Client response = clientService.save(client);

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void testDelete() {

        clientService.delete(client);

        verify(clientRepository, times(1)).delete(client);
    }

    @Test
    void testDeleteById() {

        clientService.deleteById(ID);

        verify(clientRepository, times(1)).deleteById(ID);
    }

    @Test
    void testFindByEmail() {

        when(clientRepository.findByEmail(EMAIL)).thenReturn(List.of(client));

        List<Client> response = clientService.findByEmail(EMAIL);

        assertEquals(1, response.size());
        assertEquals(client, response.get(0));
    }

    @Test
    void testUpdateByClientId() {

        when(clientRepository.save(any())).thenReturn(client);

        Client response = clientService.updateByClientId(ID, client);

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(PASSWORD, response.getPassword());

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testUpdateByClientIdPatch() {

        Client updatedClient = new Client();
        updatedClient.setName("John");

        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        Client response = clientService.updateByClientIdPatch(client, updatedClient);

        assertEquals("John", response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
    }
}