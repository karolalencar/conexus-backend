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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    public static final Long ID = 1L;
    public static final String NAME = "TestName";
    public static final String EMAIL = "test@email.com";
    public static final String CPF = "0123456789";
    public static final String PASSWORD = "password";

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private Client client;

    @BeforeEach
    void setUp() {

        client = new Client(ID, NAME, EMAIL, CPF, PASSWORD);
    }

    @Test
    void testGetAndSetRatings() {

        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(ID, 8.9, "Great service", new Professional(), client));
        client.setRatings(ratings);
        assertEquals(ratings, client.getRatings());
    }

    @Test
    void testGetAndSetServices() {

        List<Services> services = new ArrayList<>();
        services.add(new Services(ID, "address", "description", client, new Professional(), new Payment(), new Schedule()));
        client.setServices(services);
        assertEquals(services, client.getServices());
    }

    @Test
    void testEquals() {

        Client client1 = new Client(1L, "John", "john@example.com", "123456789", "password");
        Client client2 = new Client(1L, "John", "john@example.com", "123456789", "password");
        Client client3 = new Client(2L, "Jane", "jane@example.com", "987654321", "password");
        assertEquals(client1, client2);
        assertNotEquals(client1.getId(), client3.getId());
    }

    @Test
    void testCanEqual() {

        Client client1 = new Client(1L, "John", "john@example.com", "123456789", "password");
        Client client2 = new Client(1L, "John", "john@example.com", "123456789", "password");
        assertTrue(client1.canEqual(client2));
    }

    @Test
    void testHashCode() {

        Client client1 = new Client(1L, "John", "john@example.com", "123456789", "password");
        Client client2 = new Client(1L, "John", "john@example.com", "123456789", "password");
        assertEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    void testToString() {

        Client client = new Client(1L, "John", "john@example.com", "123456789", "password");
        String expectedString = "Client(id=1, name=John, email=john@example.com, cpf=123456789, password=password)";
        //assertEquals(expectedString, client.toString());
    }
}
