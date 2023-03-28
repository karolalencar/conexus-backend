package com.conexus.api.services;

import com.conexus.api.domain.Rating;
import com.conexus.api.repositories.ProfessionalRepository;
import com.conexus.api.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ProfessionalRepository professionalRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, ProfessionalRepository professionalRepository) {
        this.ratingRepository = ratingRepository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Set<Rating> findAll() {
        Set<Rating> ratings = new HashSet<>();
        ratingRepository.findAll().forEach(ratings::add);
        return ratings;
    }

    @Override
    public Rating findById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public Rating save(Rating object) {
        return ratingRepository.save(object);
    }

    @Override
    public void delete(Rating object) {
        ratingRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public List<Rating> findAllByProfessionalId(Long id) {
        return ratingRepository.findAllByProfessionalId(id);
    }
}
