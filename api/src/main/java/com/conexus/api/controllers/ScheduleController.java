package com.conexus.api.controllers;

import com.conexus.api.domain.Schedule;
import com.conexus.api.dto.ScheduleDto;
import com.conexus.api.mappers.ScheduleMapper;
import com.conexus.api.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Agendamentos")
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final ScheduleMapper scheduleMapper;

    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @Operation(summary = "Cria um agendamento")
    @PostMapping("")
    public ResponseEntity<?> createSchedule(@Valid @RequestBody ScheduleDto scheduleDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        if (!scheduleService.isProfessionalAvailable(scheduleDto.getProfessionalId(),
                scheduleDto.getStartService(), scheduleDto.getEndService())) {
            return new ResponseEntity<>("Schedule not available", HttpStatus.BAD_REQUEST);
        }

        Schedule schedule = scheduleMapper.scheduleDtoToSchedule(scheduleDto);
        Schedule newSchedule = scheduleService.save(schedule);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + newSchedule.getId()).buildAndExpand(schedule.getId()).toUri();
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna a lista de agendamentos")
    @GetMapping("")
    public ResponseEntity<?> getSchedules() {
        List<ScheduleDto> schedules = scheduleService.findAll()
                .stream()
                .map(scheduleMapper::scheduleToScheduleDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um agendamento pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Long id) {

        Schedule schedule = scheduleService.findById(id);
        ScheduleDto scheduleDto = scheduleMapper.scheduleToScheduleDto(schedule);
        return new ResponseEntity<>(scheduleDto, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um agendamento pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleDto scheduleDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Schedule schedule = scheduleService.findById(id);
        if (schedule == null) {
            return new ResponseEntity<>("Schedule not found", HttpStatus.NOT_FOUND);
        }

        if (!scheduleService.isProfessionalAvailable(scheduleDto.getProfessionalId(),
                scheduleDto.getStartService(), scheduleDto.getEndService())) {
            return new ResponseEntity<>("Schedule not available", HttpStatus.BAD_REQUEST);
        }

        Schedule updatedSchedule = scheduleMapper.scheduleDtoToSchedule(scheduleDto);
        updatedSchedule = scheduleService.updateByScheduleId(id, updatedSchedule);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("").buildAndExpand(updatedSchedule.getId()).toUri();
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }
}
