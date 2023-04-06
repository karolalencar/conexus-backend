package com.conexus.api.domain;

import com.conexus.api.repositories.ProfessionalRepository;
import com.conexus.api.repositories.RatingRepository;
import com.conexus.api.services.ProfessionalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ProfessionalTest {

    @Mock
    private ProfessionalRepository professionalRepository;

    @InjectMocks
    private ProfessionalService professionalService;

    @Mock
    private RatingRepository ratingRepository;

    Professional professional;

    @BeforeEach
    public void setUp() {
         professional = new Professional();
    }

    @Test
    public void saveProfessionalTest() {
        //Professional professionalToSave = new Professional("", "");
        //Mockito.when(professionalRepository.save(professionalToSave)).thenReturn(new User(1L, "", ""));

        //Professional savedProfessional = professionalService.saveProfessional(professionalToSave);

        //assertEquals(1L, savedProfessional.getId().longValue());
        //

        //Mockito.verify(professionalRepository).save(professionalToSave);
    }

    @Test
    public void testSetName () {
        Professional professional = new Professional();

        professional.setName("Ka");

        assertEquals("Ka", professional.getName());
    }

    @Test
    void getDescription() {
        String description = "Decoradora de festas que atende em qualquer lugar da cidade.";

        professional.setDescription(description);

        assertEquals(description, this.professional.getDescription());
    }

    @Test
    void getRatings() {
    }
}