package com.conexus.api.repositories;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProfessionalRepository extends CrudRepository<Professional, Long> {

    Page<Professional> findAllByCategory(String category, Pageable pageable);

    List<Professional> findByEmail(String email);
}
