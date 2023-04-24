package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.repositories.ProfessionalRepository;
import com.conexus.api.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private ProfessionalRepository professionalRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    private Rating rating;

    @BeforeEach
    void setUp() {

        rating = new Rating(8.5, "Test comment", new Professional(), new Client());
    }

    @Test
    void testFindAll() {

        when(ratingRepository.findAll()).thenReturn(List.of(rating));

        Set<Rating> ratings = ratingService.findAll();

        assertEquals(1, ratings.size());
    }

    @Test
    void testFindById() {

        when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));

        Rating response = ratingService.findById(1L);

        assertEquals(1L, rating.getId());
    }

    @Test
    void testSave() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findAllByProfessionalId() {
    }

    @Test
    void updateByRatingId() {
    }
}