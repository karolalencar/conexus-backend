package com.conexus.api.services;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.repositories.ProfessionalRepository;
import com.conexus.api.services.ProfessionalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
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

    @Override
    public List<Professional> findAllByCategory(String category) {

        return professionalRepository.findAllByCategory(category);
    }

    @Override
    public List<Professional> findAllByDescription(String description) {

        Set<Professional> professionals = new HashSet<>();
        professionalRepository.findAll().forEach(professionals::add);

        List<Professional> professionalList = new ArrayList<>();

        for (Professional professional : professionals) {
            if (professional.getDescription().toLowerCase().contains(description)) {
                professionalList.add(professional);
            }
        }

        return professionalList;
    }
}
