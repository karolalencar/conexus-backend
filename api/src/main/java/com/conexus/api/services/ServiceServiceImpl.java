package com.conexus.api.services;

import com.conexus.api.domain.Services;
import com.conexus.api.repositories.ServiceRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Set<Services> findAll() {
        Set<Services> services = new HashSet<>();
        serviceRepository.findAll().forEach(services::add);
        return services;
    }

    @Override
    public Services findById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public Services save(Services object) {
        return serviceRepository.save(object);
    }

    @Override
    public void delete(Services object) {
        serviceRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }


    @Override
    public List<Services> findAllByProfessionalId(Long professional_id) {
        List<Services> services = new ArrayList<>();
        serviceRepository.findAll().forEach(services::add);
        List<Services> servicesByProfessionalId = services.stream()
                .filter(service-> service.getProfessional().getId() == professional_id)
                .collect(Collectors.toList());
        return servicesByProfessionalId;
    }
}
