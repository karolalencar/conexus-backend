package com.conexus.api.services;

import com.conexus.api.domain.Professional;
import com.conexus.api.repositories.ProfessionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessionalServiceImplTest {

    @InjectMocks
    ProfessionalServiceImpl professionalService;

    @Mock
    ProfessionalRepository professionalRepository;

    Professional returnProfessional;

    @BeforeEach
    void setUp() {
        returnProfessional = Professional.builder().build();
    }

    @Test
    void findAll() {

        Set<Professional> professionals = new HashSet<>();
        professionals.add(Professional.builder().build());

        Professional professional = new Professional();
        HashSet professionalsData = new HashSet<>();
        professionalsData.add(professional);

        when(professionalRepository.findAll()).thenReturn(professionalsData);

        //Set<Professional> professionals = professionalService.findAll();

        assertEquals(professionals.size(), 1);
        verify(professionalRepository, times(1)).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void testFindAllByCategory() {
    }

    @Test
    void findAllByDescription() {
    }
}