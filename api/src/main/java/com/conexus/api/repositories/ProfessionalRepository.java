package com.conexus.api.repositories;

import com.conexus.api.domain.Professional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfessionalRepository extends CrudRepository<Professional, Long> {

    List<Professional> findAllByCategory(String category);

    List<Professional> findAllByDescription(String description);
}
