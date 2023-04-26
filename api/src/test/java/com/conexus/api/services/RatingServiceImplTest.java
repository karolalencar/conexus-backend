package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.repositories.ProfessionalRepository;
import com.conexus.api.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {

    public static final Long ID = 1L;
    public static final double RATE = 8.5;
    public static final String COMMENT = "Test comment";

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    private Rating rating;

    private Client client;

    private Professional professional;

    @BeforeEach
    void setUp() {

        client = new Client();
        professional = new Professional();

        rating = new Rating(ID, RATE, COMMENT, professional, client);
    }

    @Test
    void testFindAll() {

        when(ratingRepository.findAll()).thenReturn(List.of(rating));

        Set<Rating> response = ratingService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testFindById() {

        when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));

        Rating response = ratingService.findById(ID);

        assertNotNull(response);
        assertEquals(Rating.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(RATE, response.getRate());
        assertEquals(COMMENT, response.getComment());
        assertEquals(client, response.getClient());
        assertEquals(professional, response.getProfessional());
    }

    @Test
    void testSave() {

        when(ratingRepository.save(any())).thenReturn(rating);

        Rating response = ratingService.save(rating);

        assertNotNull(response);
        assertEquals(Rating.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(RATE, response.getRate());
        assertEquals(COMMENT, response.getComment());
        assertEquals(client, response.getClient());
        assertEquals(professional, response.getProfessional());
    }

    @Test
    void testDelete() {

        ratingService.delete(rating);

        verify(ratingRepository, times(1)).delete(rating);
    }

    @Test
    void deleteById() {

        ratingService.deleteById(ID);

        verify(ratingRepository, times(1)).deleteById(ID);
    }

    @Test
    void findAllByProfessionalId() {

        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating);

        when(ratingRepository.findAllByProfessionalId(ID)).thenReturn(ratings);

        List<Rating> result = ratingService.findAllByProfessionalId(ID);

        assertEquals(ratings, result);
    }

    @Test
    void testUpdateByRatingId() {

        when(ratingRepository.save(any())).thenReturn(rating);

        Rating response = ratingService.updateByRatingId(ID, rating);

        assertNotNull(response);
        assertEquals(Rating.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(RATE, response.getRate());
        assertEquals(COMMENT, response.getComment());
        assertEquals(client, response.getClient());
        assertEquals(professional, response.getProfessional());

        verify(ratingRepository, times(1)).save(rating);
    }
}