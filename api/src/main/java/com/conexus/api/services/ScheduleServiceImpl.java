package com.conexus.api.services;

import com.conexus.api.domain.Schedule;
import com.conexus.api.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Set<Schedule> findAll() {

        Set<Schedule> schedules = new HashSet<>();
        scheduleRepository.findAll().forEach(schedules::add);
        return schedules;
    }

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    @Override
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<Schedule> findAllByProfessionalId(Long professionalId) {
        List<Schedule> schedules = new ArrayList<>();
        scheduleRepository.findAll().forEach(schedules::add);
        List<Schedule> schedulesByProfessionalId = schedules.stream()
                .filter(schedule -> schedule.getProfessional().getId() == professionalId)
                .collect(Collectors.toList());
        return schedulesByProfessionalId;
    }

    @Override
    public Boolean isProfessionalAvailable(Long id, LocalDateTime startService, LocalDateTime endService) {
        List<Schedule> schedules = new ArrayList<>();
        scheduleRepository.findAll().forEach(schedules::add);
        List<Schedule> schedulesByProfessionalId = schedules.stream()
                .filter(schedule -> schedule.getProfessional().getId() == id)
                .collect(Collectors.toList());

        for (Schedule schedule : schedulesByProfessionalId) {

            LocalDateTime startTemp = schedule.getStartService();
            LocalDateTime endTemp = schedule.getEndService();

            if (startService.isEqual(startTemp) || endService.isEqual(endTemp)
                    || startService.isEqual(endTemp) || endService.isEqual(startTemp)
                    || (startService.isAfter(startTemp) && endService.isBefore(endTemp))
                    || (startService.isAfter(startTemp) && startService.isBefore(endTemp))
                    || (endService.isAfter(startTemp) && endService.isBefore(endTemp))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Schedule updateByScheduleId(Long id, Schedule schedule) {

        schedule.setId(id);
        return scheduleRepository.save(schedule);
    }
}
