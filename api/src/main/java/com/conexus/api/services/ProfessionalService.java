package com.conexus.api.services;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.dto.ProfessionalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfessionalService extends CrudService<Professional, Long> {

    List<Professional> findByEmail(String email);

    Page<ProfessionalDto> findAllByCategory(String category, Pageable pageable);

    List<Professional> findAllByDescription(String description);

    Professional updateByProfessionalId(Long id, Professional professional);

    Professional updateByProfessionalIdPatch(Professional professional, Professional updatedprofessional);
}
