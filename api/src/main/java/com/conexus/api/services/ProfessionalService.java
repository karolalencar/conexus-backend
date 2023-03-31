package com.conexus.api.services;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;

import java.util.List;

public interface ProfessionalService extends CrudService<Professional, Long> {

    List<Professional> findAllByCategory(String category);

    List<Professional> findAllByDescription(String description);
}
