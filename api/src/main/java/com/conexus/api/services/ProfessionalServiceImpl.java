package com.conexus.api.services;

import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.repositories.ProfessionalRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final ProfessionalMapper professionalMapper;

    @Resource
    private BCryptPasswordEncoder encoder;

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
    public Professional save(Professional professional) {
        return professionalRepository.save(professional);
    }

    @Override
    public void delete(Professional professional) {
        professionalRepository.delete(professional);
    }

    @Override
    public void deleteById(Long id) {
        professionalRepository.deleteById(id);
    }

    @Override
    public List<Professional> findByEmail(String email) {

        List<Professional> professional = professionalRepository.findByEmail(email);
        return professional;
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

    @Override
    public Professional updateByProfessionalId(Long id, Professional professional) {

        professional.setId(id);
        return professionalRepository.save(professional);
    }

    @Override
    public Professional updateByProfessionalIdPatch(Professional professional, Professional updatedprofessional) {

        updatedprofessional.setId(professional.getId());

        if (updatedprofessional.getName() == null) {
            updatedprofessional.setName(professional.getName());
        }

        if (updatedprofessional.getCpf() == null) {
            updatedprofessional.setCpf(professional.getCpf());
        }

        if (updatedprofessional.getName() == null) {
            updatedprofessional.setName(professional.getName());
        }

        if (updatedprofessional.getCategory() == null) {
            updatedprofessional.setCategory(professional.getCategory());
        }

        if (updatedprofessional.getDescription() == null) {
            updatedprofessional.setDescription(professional.getDescription());
        }

        if (updatedprofessional.getEmail() == null) {
            updatedprofessional.setEmail(professional.getEmail());
        }

        if (updatedprofessional.getPassword() == null) {
            updatedprofessional.setPassword(encoder.encode(professional.getPassword()));
            System.out.println("aaaaaaaaaaaaaaaaaa");
        }

        return professionalRepository.save(updatedprofessional);
    }
}
