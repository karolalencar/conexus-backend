package com.conexus.api.controllers;


import com.conexus.api.dto.ServiceDto;
import com.conexus.api.mappers.ServiceMapper;
import com.conexus.api.services.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
