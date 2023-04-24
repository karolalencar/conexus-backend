package com.conexus.api.services;

import com.conexus.api.domain.Schedule;
import com.conexus.api.domain.Services;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService extends CrudService<Schedule, Long> {

    List<Schedule> findAllByProfessionalId(Long professionalId);

    Boolean isProfessionalAvailable(Long id, LocalDateTime startService, LocalDateTime endService);

    Schedule updateByScheduleId(Long id, Schedule schedule);
}
