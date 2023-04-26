package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.repositories.ProfessionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

    @Mock
    private ProfessionalMapper professionalMapper;

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
    void testFindByEmail() {

        when(professionalRepository.findByEmail(EMAIL)).thenReturn(List.of(professional));

        List<Professional> response = professionalService.findByEmail(EMAIL);

        assertEquals(1, response.size());
        assertEquals(professional, response.get(0));
    }

    @Test
    void testFindAllByCategory() {
     }


    @Test
    void testFindAllByDescription() {

        List<Professional> professionalList = new ArrayList<>();
        professionalList.add(professional);

        when(professionalRepository.findAll()).thenReturn(professionalList);

        List<Professional> result = professionalService.findAllByDescription("test");

        assertEquals(1, result.size());
    }

    @Test
    void testUpdateByProfessionalId() {

        when(professionalRepository.save(any())).thenReturn(professional);

        Professional response = professionalService.updateByProfessionalId(ID, professional);

        assertNotNull(response);
        assertEquals(Professional.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CPF, response.getCpf());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(CATEGORY, response.getCategory());
        assertEquals(DESCRIPTION, response.getDescription());

        verify(professionalRepository, times(1)).save(professional);
    }

    @Test
    void testUpdateByProfessionalIdPatch() {

        Professional updatedProfessional = new Professional();
        updatedProfessional.setName("John");

        when(professionalRepository.save(any(Professional.class))).thenReturn(updatedProfessional);

        Professional response = professionalService.updateByProfessionalIdPatch(professional, updatedProfessional);

        assertEquals("John", response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(CATEGORY, response.getCategory());
        assertEquals(DESCRIPTION, response.getDescription());
    }
}