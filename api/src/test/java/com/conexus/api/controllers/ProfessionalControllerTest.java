package com.conexus.api.controllers;

import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ClientDto;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.mappers.RatingMapper;
import com.conexus.api.services.ProfessionalService;
import com.conexus.api.services.ProfessionalServiceImpl;
import com.conexus.api.services.RatingService;
import com.conexus.api.services.ServiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.jboss.logging.MDC.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
public class ProfessionalControllerTest {

    public static final Long ID = 1L;
    public static final String NAME = "TestName";
    public static final String EMAIL = "test@email.com";
    public static final String CPF = "0123456789";
    public static final String PASSWORD = "password";
    public static final String DESCRIPTION = "Test description";
    public static final String CATEGORY = "teacher";

    @InjectMocks
    private ProfessionalController professionalController;

    @Mock
    private ProfessionalServiceImpl professionalService;

    @Mock
    ProfessionalMapper professionalMapper;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RatingService ratingService;

    @Mock
    private ServiceService serviceService;

    @Mock
    private RatingMapper ratingMapper;

    private Professional professional;

    private ProfessionalDto professionalDto;

    @BeforeEach
    public void setUp() {

        professional = new Professional(ID, NAME, EMAIL, CPF, PASSWORD, CATEGORY, DESCRIPTION);
        professionalDto = new ProfessionalDto(ID, NAME, EMAIL, CPF, PASSWORD, CATEGORY, DESCRIPTION);
    }

    @Test
    void testCreateProfessionalSuccess() {

        when(professionalMapper.professionalDtoToProfessional(any(ProfessionalDto.class))).thenReturn(professional);
        when(professionalService.save(any(Professional.class))).thenReturn(professional);

        ResponseEntity<?> responseEntity = professionalController.createProfessional(professionalDto, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testGetProfessionalsSuccess() {

        when(professionalService.findAll()).thenReturn(Set.of(professional));

        ResponseEntity<List<ProfessionalDto>> responseEntity = professionalController.getProfessionals();

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(ResponseEntity.class, responseEntity.getClass());
        assertEquals(ArrayList.class, responseEntity.getBody().getClass());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    void testGetProfessional() {

        when(professionalService.findById(ID)).thenReturn(professional);
        when(professionalMapper.professionalToProfessionalDto(professional)).thenReturn(professionalDto);

        ResponseEntity<ProfessionalDto> responseEntity = professionalController.getProfessional(ID);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(ResponseEntity.class, responseEntity.getClass());
        assertEquals(ProfessionalDto.class, responseEntity.getBody().getClass());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(ID, responseEntity.getBody().getId());
        assertEquals(NAME, responseEntity.getBody().getName());
        assertEquals(EMAIL, responseEntity.getBody().getEmail());
        assertEquals(CPF, responseEntity.getBody().getCpf());
        assertEquals(PASSWORD, responseEntity.getBody().getPassword());
        assertEquals(CATEGORY, responseEntity.getBody().getCategory());
        assertEquals(DESCRIPTION, responseEntity.getBody().getDescription());
    }

    @Test
    void testSearchAllProfessionalsByCategory() {
    }

    @Test
    void testGetAllProfessionalsByDescription() {
    }

    @Test
    void testGetRatingsByProfessional() {
    }

    /*@Test
    void testUpdateProfessionalSuccess() {

        when(professionalService.findById(ID)).thenReturn(professional);
        when(professionalService.updateByProfessionalId(ID, professional)).thenReturn(professional);

        ResponseEntity<?> responseEntity = professionalController.updateProfessional(ID, professionalDto, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        verify(professionalService, times(1)).updateByProfessionalId(ID, professional);
        verify(professionalService, times(1)).findById(ID);
    }*/

    @Test
    void testUpdateProfessionalPatch() {
    }

    /*@Test
    void testDeleteProfessionalSuccess() {

        when(professionalService.findById(ID)).thenReturn(professional);

        ResponseEntity<?> responseEntity = professionalController.deleteProfessional(ID);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(professionalService, times(1)).deleteById(ID);
    }*/
}
