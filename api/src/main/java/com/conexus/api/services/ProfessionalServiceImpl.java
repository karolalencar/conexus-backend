package com.conexus.api.services;

import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.repositories.ProfessionalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final ProfessionalMapper professionalMapper;

    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository, ProfessionalMapper professionalMapper) {
        this.professionalRepository = professionalRepository;
        this.professionalMapper = professionalMapper;
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
    public Page<ProfessionalDto> findAllByCategory(String category, Pageable pageable) {

       Page<Professional> professionals = professionalRepository.findAllByCategory(category, pageable);
       return professionals.map(professionalMapper::professionalToProfessionalDto);
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
