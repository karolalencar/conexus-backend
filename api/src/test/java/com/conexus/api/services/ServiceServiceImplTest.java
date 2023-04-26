package com.conexus.api.services;

import com.conexus.api.domain.*;
import com.conexus.api.repositories.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceServiceImplTest {

    public static final long ID = 1L;
    public static final String ADDRESS = "Test Address";
    public static final String DESCRIPTION = "Test Description";

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private Services service;

    private Professional professional;

    private Client client;

    @BeforeEach
    void setUp() {

        professional = new Professional();
        professional.setId(ID);
        client = new Client();
        client.setId(ID);

        service = new Services(ID, ADDRESS, DESCRIPTION, client, professional, new Payment(), new Schedule());
    }

    @Test
    void testFindAll() {

        when(serviceRepository.findAll()).thenReturn(List.of(service));

        Set<Services> response = serviceService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testFindById() {

        when(serviceRepository.findById(anyLong())).thenReturn(Optional.of(service));

        Services response = serviceService.findById(ID);

        assertNotNull(response);
        assertEquals(Services.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(ADDRESS, response.getAddress());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(client, response.getClient());
        assertEquals(professional, response.getProfessional());
    }

    @Test
    void testSave() {

        when(serviceRepository.save(any())).thenReturn(service);

        Services response = serviceService.save(service);

        assertNotNull(response);
        assertEquals(Services.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(ADDRESS, response.getAddress());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(client, response.getClient());
        assertEquals(professional, response.getProfessional());
    }

    @Test
    void testDelete() {

        serviceService.delete(service);

        verify(serviceRepository, times(1)).delete(service);
    }

    @Test
    void testDeleteById() {

        serviceService.deleteById(ID);

        verify(serviceRepository, times(1)).deleteById(ID);
    }

    @Test
    void testFindAllByProfessionalId() {

        List<Services> services = new ArrayList<>();
        services.add(service);

        when(serviceRepository.findAll()).thenReturn(services);

        List<Services> result = serviceService.findAllByProfessionalId(ID);

        assertEquals(1, result.size());
        assertEquals(ID, result.get(0).getId());
    }

    @Test
    void testFindAllByClientId() {

        List<Services> services = new ArrayList<>();
        services.add(service);

        when(serviceRepository.findAll()).thenReturn(services);

        List<Services> result = serviceService.findAllByClientId(ID);

        assertEquals(1, result.size());
        assertEquals(ID, result.get(0).getId());
    }

    @Test
    void testUpdateByServiceId() {

        when(serviceRepository.save(any())).thenReturn(service);

        Services response = serviceService.updateByServiceId(ID, service);

        assertNotNull(response);
        assertEquals(Services.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(ADDRESS, response.getAddress());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(client, response.getClient());
        assertEquals(professional, response.getProfessional());

        verify(serviceRepository, times(1)).save(service);
    }
}