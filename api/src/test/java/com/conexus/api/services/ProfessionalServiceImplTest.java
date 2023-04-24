package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.repositories.ProfessionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProfessionalServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "TestName";
    public static final String EMAIL = "test@email.com";
    public static final String CPF = "0123456789";
    public static final String PASSWORD = "password";
    public static final String DESCRIPTION = "Test description";
    public static final String CATEGORY = "teacher";

    @Mock
    private ProfessionalRepository professionalRepository;

    @InjectMocks
    private ProfessionalServiceImpl professionalService;

    private Professional professional;

    @BeforeEach
    void setUp() {

        professional = new Professional(ID, NAME, EMAIL, CPF,  PASSWORD, CATEGORY, DESCRIPTION);
    }

    @Test
    void testFindAll() {

        when(professionalRepository.findAll()).thenReturn(List.of(professional));

        Set<Professional> response = professionalService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testFindById() {

        when(professionalRepository.findById(anyLong())).thenReturn(Optional.of(professional));

        Professional response = professionalService.findById(ID);

        assertNotNull(response);

        assertEquals(Professional.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(CATEGORY, response.getCategory());
    }

    @Test
    void testSave() {

        when(professionalRepository.save(any())).thenReturn(professional);

        Professional response = professionalService.save(professional);

        assertNotNull(response);
        assertEquals(Professional.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(CATEGORY, response.getCategory());
    }

    @Test
    void testDelete() {

        professionalService.delete(professional);

        verify(professionalRepository, times(1)).delete(professional);
    }

    @Test
    void testDeleteById() {

        professionalService.deleteById(ID);

        verify(professionalRepository, times(1)).deleteById(ID);
    }

    @Test
    void testFindAllByCategory() {
    }

    @Test
    void findAllByDescription() {
    }

    @Test
    void testFindByEmail() {

        when(professionalRepository.findByEmail(EMAIL)).thenReturn(List.of(professional));

        List<Professional> response = professionalRepository.findByEmail(EMAIL);

        assertEquals(1, response.size());
        assertEquals(professional, response.get(0));
    }

    @Test
    void testFindAllByDescription() {
    }


    @Test
    void testUpdateByProfessionalId() {
    }

    @Test
    void testUpdateByProfessionalIdPatch() {
    }
}