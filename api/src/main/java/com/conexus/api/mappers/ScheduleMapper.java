package com.conexus.api.mappers;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Schedule;
import com.conexus.api.dto.ScheduleBetweenDto;
import com.conexus.api.dto.ScheduleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(source = "schedule.professional.id", target = "professionalId")
    ScheduleDto scheduleToScheduleDto(Schedule schedule);

    @Mapping(source = "scheduleDto.professionalId", target = "professional")
    Schedule scheduleDtoToSchedule(ScheduleDto scheduleDto);

    Schedule scheduleDtoToEntity(ScheduleBetweenDto scheduleBetweenDto);

    @Mapping(source = "professionalId", target = "id")
    Professional toProfessional(Long professionalId);

}
