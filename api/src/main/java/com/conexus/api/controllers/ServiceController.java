package com.conexus.api.controllers;


import com.conexus.api.domain.Services;
import com.conexus.api.dto.ServiceDto;
import com.conexus.api.mappers.ServiceMapper;
import com.conexus.api.services.ScheduleService;
import com.conexus.api.services.ServiceService;
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
@Tag(name = "Serviços")
@RequestMapping("/services")
public class ServiceController {

    private final ServiceMapper serviceMapper;
    private final ServiceService serviceService;
    private final ScheduleService scheduleService;

    public ServiceController(ServiceMapper serviceMapper, ServiceService serviceService, ScheduleService scheduleService) {
        this.serviceMapper = serviceMapper;
        this.serviceService = serviceService;
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "Cria um novo serviço")
    @PostMapping("")
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceDto serviceDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }


        Services service = serviceMapper.serviceDtoToService(serviceDto);
        Services newService = serviceService.save(service);
        return new ResponseEntity<>(serviceMapper.serviceToServiceDto(newService), HttpStatus.CREATED);

    }

    @Operation(summary = "Retorna a lista de todos os serviços")
    @GetMapping("")
    public ResponseEntity<List<ServiceDto>> getServices() {
        List<ServiceDto> services = serviceService.findAll()
                .stream()
                .map(serviceMapper::serviceToServiceDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(services);
    }

    @Operation(summary = "Retorna um serviço pelo id")
    @GetMapping("/{id}")
    public ServiceDto getService(@PathVariable Long id) {
        Services service = serviceService.findById(id);
        ServiceDto serviceDto = serviceMapper.serviceToServiceDto(service);
        return serviceDto;
    }

    @Operation(summary = "Atualiza um serviço pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @Valid @RequestBody ServiceDto serviceDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Services service = serviceService.findById(id);
        if (service == null) {
            return new ResponseEntity<>("Service not found", HttpStatus.NOT_FOUND);
        }

        service = serviceMapper.serviceDtoToService(serviceDto);
        Services updatedService = serviceService.updateByServiceId(id, service);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("").buildAndExpand(updatedService.getId()).toUri();
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta um serviço pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        Services service = serviceService.findById(id);
        if (service == null) {
            return ResponseEntity.notFound().build();
        }

        serviceService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
