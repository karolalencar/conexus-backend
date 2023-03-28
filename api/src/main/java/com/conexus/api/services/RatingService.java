package com.conexus.api.services;

import com.conexus.api.domain.Rating;

import java.util.List;

public interface RatingService extends CrudService<Rating, Long> {

    List<Rating> findAllByProfessionalId(Long id);
}
