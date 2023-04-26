package com.conexus.api.mappers;

import com.conexus.api.domain.*;
import com.conexus.api.dto.ServiceBetweenDto;
import com.conexus.api.dto.ServiceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServiceMapperTest {

    public static final Long ID = 1L;
    public static final String ADDRESS = "Rua X";
    public static final String DESCRIPTION = "Test description";

    @InjectMocks
    private ServiceMapperImpl serviceMapper;

    private Services service;

    private ServiceDto serviceDto;

    private ServiceBetweenDto serviceBetweenDto;

    private Client client;

    private Professional professional;

    private Payment payment;

    private Schedule schedule;

    @BeforeEach
    void setUp() {

        client = new Client();
        client.setId(ID);
        professional = new Professional();
        professional.setId(ID);
        payment = new Payment();
        payment.setId(ID);
        schedule = new Schedule();
        schedule.setId(ID);

        service = new Services(ID, ADDRESS, DESCRIPTION, client, professional, payment, schedule);

        serviceDto = new ServiceDto(ID, ADDRESS, DESCRIPTION, ID, ID, ID, ID);

        serviceBetweenDto = new ServiceBetweenDto(ID, ADDRESS, DESCRIPTION, client, professional, payment, schedule);
    }

    @Test
    void serviceToServiceDto() {

        ServiceDto resultServiceDto = serviceMapper.serviceToServiceDto(service);

        assertEquals(ID, resultServiceDto.getId());
        assertEquals(ADDRESS, resultServiceDto.getAddress());
        assertEquals(DESCRIPTION, resultServiceDto.getDescription());
        assertEquals(ID, resultServiceDto.getClientId());
        assertEquals(ID, resultServiceDto.getProfessionalId());
        assertEquals(ID, resultServiceDto.getPaymentId());
        assertEquals(ID, resultServiceDto.getScheduleId());
    }

    @Test
    void testServiceDtoToService() {

        Services resultService = serviceMapper.serviceDtoToService(serviceDto);

        assertEquals(ID, resultService.getId());
        assertEquals(ADDRESS, resultService.getAddress());
        assertEquals(DESCRIPTION, resultService.getDescription());
        assertEquals(client, resultService.getClient());
        assertEquals(professional, resultService.getProfessional());
        assertEquals(payment, resultService.getPayment());
        assertEquals(schedule, resultService.getSchedule());
    }

    @Test
    void testServiceDtoToEntity() {

        Services resultService = serviceMapper.serviceDtoToEntity(serviceBetweenDto);

        assertEquals(client, resultService.getClient());
        assertEquals(professional, resultService.getProfessional());
        assertEquals(payment, resultService.getPayment());
        assertEquals(schedule, resultService.getSchedule());
    }

    @Test
    void testToClient() {

        Client result = serviceMapper.toClient(ID);
        assertEquals(client, result);
    }

    @Test
    void testToProfessional() {

        Professional result = serviceMapper.toProfessional(ID);
        assertEquals(professional, result);
    }

    @Test
    void testToPayment() {

        Payment result = serviceMapper.toPayment(ID);
        assertEquals(payment, result);
    }

    @Test
    void testToSchedule() {

        Schedule result = serviceMapper.toSchedule(ID);
        assertEquals(schedule, result);
    }
}