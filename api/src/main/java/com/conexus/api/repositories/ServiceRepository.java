package com.conexus.api.repositories;

import com.conexus.api.domain.Services;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Services, Long> {

    List<Services> findAllByProfessionalId(Long professional_id);
}
