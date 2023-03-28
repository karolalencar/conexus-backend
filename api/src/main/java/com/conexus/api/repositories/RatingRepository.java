package com.conexus.api.repositories;

import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    List<Rating> findAllByProfessionalId(Long id);
}
