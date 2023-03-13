package com.conexus.api.services;

import com.conexus.api.domain.Rating;
import com.conexus.api.repositories.RatingRepository;
import com.conexus.api.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Set<Rating> findAll() {
        Set<Rating> ratings = new HashSet<>();
        ratingRepository.findAll().forEach(ratings::add);
        return ratings;
    }

    @Override
    public Rating findById(Long aLong) {
        return null;
    }

    @Override
    public Rating save(Rating object) {
        return null;
    }

    @Override
    public void delete(Rating object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
