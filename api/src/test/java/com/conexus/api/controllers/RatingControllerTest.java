package com.conexus.api.controllers;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.dto.ClientDto;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.dto.RatingDto;
import com.conexus.api.mappers.RatingMapper;
import com.conexus.api.services.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

class RatingControllerTest {

    public static final Long ID = 1L;
    public static final double RATE = 8.5;
    public static final String COMMENT = "Test comment";

    @InjectMocks
    private RatingController ratingController;

    @Mock
    private RatingServiceImpl ratingService;

    @Mock
    private RatingMapper ratingMapper;

    @Mock
    private BindingResult bindingResult;

    private Rating rating;

    private RatingDto ratingDto;

    private Professional professional;

    private ProfessionalDto professionalDto;

    private Client client;

    private ClientDto clientDto;

    @BeforeEach
    void setUp() {



        rating = new Rating(ID, RATE, COMMENT, new Professional(), new Client());

    }

    @Test
    void createRating() {
    }

    @Test
    void getRatings() {
    }

    @Test
    void updateRating() {
    }

    @Test
    void deleteRating() {
    }
}