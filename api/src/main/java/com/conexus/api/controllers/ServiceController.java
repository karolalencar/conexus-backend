package com.conexus.api.controllers;


import com.conexus.api.domain.Services;
import com.conexus.api.dto.ServiceDto;
import com.conexus.api.mappers.ServiceMapper;
import com.conexus.api.services.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Serviços")
@RequestMapping("/services")
public class ServiceController {

    private final ServiceMapper serviceMapper;
    private final ServiceService serviceService;

    public ServiceController(ServiceMapper serviceMapper, ServiceService serviceService) {
        this.serviceMapper = serviceMapper;
        this.serviceService = serviceService;
    }

    @Operation(summary = "Retorna a lista de todos os serviços")
    @GetMapping("")
    public List<ServiceDto> getServices() {
        List<ServiceDto> services = serviceService.findAll()
                .stream()
                .map(serviceMapper::serviceToServiceDto)
                .collect(Collectors.toList());
        return services;
    }

    @Operation(summary = "Retorna um serviço pelo id")
    @GetMapping("/{id}")
    public ServiceDto getService(@PathVariable Long id) {
        Services service = serviceService.findById(id);
        ServiceDto serviceDto = serviceMapper.serviceToServiceDto(service);
        return serviceDto;
    }

    @Operation(summary = "Cria um novo serviço")
    @PostMapping("")
    public ResponseEntity<?> createService(@RequestBody ServiceDto serviceDto) {
        try {
            Services service = serviceMapper.serviceDtoToService(serviceDto);
            Services newService = serviceService.save(service);
            return ResponseEntity.ok(newService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating service: " + e.getMessage());
        }
    }
}
