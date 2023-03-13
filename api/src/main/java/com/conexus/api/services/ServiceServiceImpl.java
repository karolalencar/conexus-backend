package com.conexus.api.services;

import com.conexus.api.domain.Service;
import com.conexus.api.repositories.ServiceRepository;
import com.conexus.api.services.ServiceService;


import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Set<Service> findAll() {
        Set<Service> services = new HashSet<>();
        serviceRepository.findAll().forEach(services::add);
        return services;
    }

    @Override
    public Service findById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public Service save(Service object) {
        return serviceRepository.save(object);
    }

    @Override
    public void delete(Service object) {
        serviceRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }
}
