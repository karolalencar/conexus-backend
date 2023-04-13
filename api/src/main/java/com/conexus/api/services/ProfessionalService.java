package com.conexus.api.services;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.dto.ProfessionalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfessionalService extends CrudService<Professional, Long> {

    Page<ProfessionalDto> findAllByCategory(String category, Pageable pageable);

    List<Professional> findAllByDescription(String description);
}
