package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Schedule;
import com.conexus.api.repositories.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    public static final Long ID = 1L;
    public static final LocalDateTime  START = LocalDateTime.of(2023, 5, 10, 14, 50, 0);
    public static final LocalDateTime  END = LocalDateTime.of(2023, 5, 10, 15, 50, 0);

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    private Schedule schedule;

    private Professional professional;

    @BeforeEach
    void setUp() {

        professional = new Professional(ID, "name", "email@email.com", "cpf", "password", "category", "description");

        schedule = new Schedule(ID, START, END, professional);
    }

    @Test
    void testFindAll() {

        when(scheduleRepository.findAll()).thenReturn(List.of(schedule));

        Set<Schedule> response = scheduleService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testFindById() {

        when(scheduleRepository.findById(anyLong())).thenReturn(Optional.of(schedule));

        Schedule response = scheduleService.findById(ID);

        assertNotNull(response);
        assertEquals(Schedule.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(START, response.getStartService());
        assertEquals(END, response.getEndService());
        assertEquals(professional, response.getProfessional());
    }

    @Test
    void testSave() {

        when(scheduleRepository.save(any())).thenReturn(schedule);

        Schedule response = scheduleService.save(schedule);

        assertNotNull(response);
        assertEquals(Schedule.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(START, response.getStartService());
        assertEquals(END, response.getEndService());
        assertEquals(professional, response.getProfessional());
    }

    @Test
    void testDelete() {

        scheduleService.delete(schedule);

        verify(scheduleRepository, times(1)).delete(schedule);
    }

    @Test
    void testDeleteById() {

        scheduleService.deleteById(ID);

        verify(scheduleRepository, times(1)).deleteById(ID);
    }

    @Test
    void testFindAllByProfessionalId() {

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule);

        when(scheduleRepository.findAll()).thenReturn(schedules);

        List<Schedule> result = scheduleService.findAllByProfessionalId(ID);

        assertEquals(1, result.size());
        assertEquals(ID, result.get(0).getId());
        assertEquals(START, result.get(0).getStartService());
        assertEquals(END, result.get(0).getEndService());
    }

    @Test
    void testIsProfessionalAvailable() {

        Schedule overlappingSchedule1 = new Schedule();
        overlappingSchedule1.setProfessional(professional);
        overlappingSchedule1.setStartService(LocalDateTime.of(2023, 5, 10, 14, 0));
        overlappingSchedule1.setEndService(LocalDateTime.of(2023, 5, 10, 14, 30));

        Schedule overlappingSchedule2 = new Schedule();
        overlappingSchedule2.setProfessional(professional);
        overlappingSchedule2.setStartService(LocalDateTime.of(2023, 5, 10, 15, 0));
        overlappingSchedule2.setEndService(LocalDateTime.of(2023, 5, 10, 16, 20));

        Schedule nonOverlappingSchedule = new Schedule();
        nonOverlappingSchedule.setProfessional(professional);
        nonOverlappingSchedule.setStartService(LocalDateTime.of(2023, 5, 10, 8, 0));
        nonOverlappingSchedule.setEndService(LocalDateTime.of(2023, 5, 10, 8, 30));

        when(scheduleRepository.findAll()).thenReturn(Arrays.asList(overlappingSchedule1, overlappingSchedule2, nonOverlappingSchedule));

        assertFalse(scheduleService.isProfessionalAvailable(ID, START, END));

        assertFalse(scheduleService.isProfessionalAvailable(ID, START, END));

        assertFalse(scheduleService.isProfessionalAvailable(ID, START, END));
    }

    @Test
    void testUpdateByScheduleId() {

        when(scheduleRepository.save(any())).thenReturn(schedule);

        Schedule response = scheduleService.updateByScheduleId(ID, schedule);

        assertNotNull(response);
        assertEquals(Schedule.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(START, response.getStartService());
        assertEquals(END, response.getEndService());
        assertEquals(professional, response.getProfessional());

        verify(scheduleRepository, times(1)).save(schedule);
    }
}