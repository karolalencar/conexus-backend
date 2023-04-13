package com.conexus.api.domain;

import com.conexus.api.repositories.ProfessionalRepository;
import com.conexus.api.repositories.RatingRepository;
import com.conexus.api.services.ProfessionalService;
import com.conexus.api.services.ProfessionalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfessionalTest {

    @Mock
    private ProfessionalRepository professionalRepository;

    @InjectMocks
    private ProfessionalServiceImpl professionalService;

    @Mock
    private RatingRepository ratingRepository;

    private Professional professional;

    @BeforeEach
    public void setUp() {
         professional = new Professional();

         professional.setId(1L);
         professional.setName("Ka");
         professional.setCpf("7438799");
         professional.setEmail("ka@gmail.com");
    }

    //@Test
    //public void saveProfessionalTest() {
        //Professional professionalToSave = new Professional("", "");
        //Mockito.when(professionalRepository.save(professionalToSave)).thenReturn(new User(1L, "", ""));

        //Professional savedProfessional = professionalService.saveProfessional(professionalToSave);

        //assertEquals(1L, savedProfessional.getId().longValue());
        //

        //Mockito.verify(professionalRepository).save(professionalToSave);
    //}

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

    @Test
    public void saveProfessionalTest() {
        when(professionalRepository.save(professional)).thenReturn(professional);

        Professional createdProfessional = professionalService.save(professional);

        assertEquals(1L, createdProfessional.getId());
        assertEquals("Ka", createdProfessional.getName());
        assertEquals("7438799", createdProfessional.getCpf());
    }

    @Test
    void findProfessionalByIdTest() {
        when(professionalRepository.findById(1L)).thenReturn(Optional.of(professional));

        Professional retrievedProfessional = professionalService.findById(1L);

        assertEquals("Ka", retrievedProfessional.getName());
    }

    @Test
    void findAllProfessionalsTest() {
        Professional professional2 = new Professional();
        professional2.setDescription("Chef de cozinha");

        List<Professional> professionals = Arrays.asList(professional, professional2);

        when(professionalRepository.findAll()).thenReturn(professionals);

        Set<Professional> retrievedProfessionals = professionalService.findAll();

        assertEquals(2, retrievedProfessionals.size());
        assertEquals(true, retrievedProfessionals.contains(professional2));
    }
}