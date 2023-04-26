package com.conexus.api.mappers;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.dto.ClientDto;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.dto.RatingBetweenDto;
import com.conexus.api.dto.RatingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingMapperTest {

    public static final Long ID = 1L;
    public static final Double RATE = 8.0;
    public static final String COMMENT = "Test comment";

    @InjectMocks
    private RatingMapperImpl ratingMapper;

    private Rating rating;

    private RatingDto ratingDto;

    private Professional professional;

    private ProfessionalDto professionalDto;

    private Client client;

    private ClientDto clientDto;

    private RatingBetweenDto ratingBetweenDto;

    @BeforeEach
    void setUp() {

        professional = new Professional();
        professional.setId(ID);
        client = new Client();
        client.setId(ID);

        rating = new Rating(ID, RATE, COMMENT, professional, client);

        professionalDto = new ProfessionalDto();
        professionalDto.setId(ID);
        clientDto = new ClientDto();
        clientDto.setId(ID);

        ratingDto = new RatingDto(ID, RATE, COMMENT, ID, ID);

        ratingBetweenDto = new RatingBetweenDto(ID, RATE, COMMENT, professional, client);
    }

    @Test
    void testRatingToRatingDto() {

        RatingDto resultRatingDto = ratingMapper.ratingToRatingDto(rating);

        assertEquals(ID, resultRatingDto.getId());
        assertEquals(RATE, resultRatingDto.getRate());
        assertEquals(COMMENT, resultRatingDto.getComment());
        assertEquals(ID, resultRatingDto.getId());
        assertEquals(ID, resultRatingDto.getProfessionalId());
        assertEquals(ID, resultRatingDto.getClientId());
    }

    @Test
    void testRatingDtoToRating() {

        Rating resultRating = ratingMapper.ratingDtoToRating(ratingDto);

        assertEquals(RATE, resultRating.getRate());
        assertEquals(COMMENT, resultRating.getComment());
        assertEquals(client, resultRating.getClient());
        assertEquals(professional, resultRating.getProfessional());
    }

    @Test
    void testRatingDtoToEntity() {

        Rating resultRating = ratingMapper.ratingDtoToEntity(ratingBetweenDto);

        assertEquals(professional, resultRating.getProfessional());
        assertEquals(client, resultRating.getClient());
    }

    @Test
    void testToProfessional() {

        Professional result = ratingMapper.toProfessional(ID);
        assertEquals(professional, result);
    }

    @Test
    void testToClient() {

        Client result = ratingMapper.toClient(ID);
        assertEquals(client, result);
    }
}