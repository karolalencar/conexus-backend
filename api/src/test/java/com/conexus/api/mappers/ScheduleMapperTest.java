package com.conexus.api.mappers;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Schedule;
import com.conexus.api.dto.ScheduleBetweenDto;
import com.conexus.api.dto.ScheduleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleMapperTest {

    public static final Long ID = 1L;
    public static final LocalDateTime START = LocalDateTime.of(2023, 5, 10, 10, 20, 0);
    public static final LocalDateTime END = LocalDateTime.of(2023, 5, 10, 10, 50, 0);

    @InjectMocks
    private ScheduleMapperImpl scheduleMapper;

    private Schedule schedule;

    private ScheduleDto scheduleDto;

    private ScheduleBetweenDto scheduleBetweenDto;

    private Professional professional;

    @BeforeEach
    void setUp() {

        professional = new Professional();
        professional.setId(ID);

        schedule = new Schedule(ID, START, END, professional);

        scheduleDto = new ScheduleDto(ID, START, END, ID);

        scheduleBetweenDto = new ScheduleBetweenDto(ID, START, END, professional);
    }

    @Test
    void testScheduleToScheduleDto() {

        ScheduleDto resultScheduleDto = scheduleMapper.scheduleToScheduleDto(schedule);

        assertEquals(ID, resultScheduleDto.getId());
        assertEquals(START, resultScheduleDto.getStartService());
        assertEquals(END, resultScheduleDto.getEndService());
        assertEquals(ID, resultScheduleDto.getProfessionalId());
    }

    @Test
    void testScheduleDtoToSchedule() {

        Schedule resultSchedule = scheduleMapper.scheduleDtoToSchedule(scheduleDto);

        assertEquals(ID, resultSchedule.getId());
        assertEquals(START, resultSchedule.getStartService());
        assertEquals(END, resultSchedule.getEndService());
        assertEquals(professional, resultSchedule.getProfessional());
    }

    @Test
    void testScheduleDtoToEntity() {

        Schedule resultSchedule = scheduleMapper.scheduleDtoToEntity(scheduleBetweenDto);

        assertEquals(ID, resultSchedule.getId());
        assertEquals(START, resultSchedule.getStartService());
        assertEquals(END, resultSchedule.getEndService());
        assertEquals(professional, resultSchedule.getProfessional());
    }

    @Test
    void testToProfessional() {

        Professional result = scheduleMapper.toProfessional(ID);
        assertEquals(professional, result);
    }
}