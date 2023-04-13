package com.conexus.api.domain;

import com.conexus.api.repositories.ClientRepository;
import com.conexus.api.services.ClientService;
import com.conexus.api.services.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientServiceImpl clientServiceImpl;

    Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
    }

    @Test
    void deleteByIdTest() {
        clientServiceImpl.deleteById(1L);

        verify(clientRepository, times(1)).deleteById(1L);
    }
}
