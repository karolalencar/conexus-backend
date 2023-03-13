package com.conexus.api.services.impl;

import com.conexus.api.domain.Professional;
import com.conexus.api.repositories.ProfessionalRepository;
import com.conexus.api.services.ProfessionalService;

import java.util.HashSet;
import java.util.Set;

public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;

    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Set<Professional> findAll() {
        Set<Professional> professionals = new HashSet<>();
        professionalRepository.findAll().forEach(professionals::add);
        return professionals;
    }

    @Override
    public Professional findById(Long id) {
        return professionalRepository.findById(id).orElse(null);
    }

    @Override
    public Professional save(Professional object) {
        return professionalRepository.save(object);
    }

    @Override
    public void delete(Professional object) {
        professionalRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        professionalRepository.deleteById(id);
    }
}
