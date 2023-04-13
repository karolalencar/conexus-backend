package com.conexus.api.controllers;

import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.mappers.RatingMapper;
import com.conexus.api.services.ProfessionalService;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.jboss.logging.MDC.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class ProfessionalControllerTest {

    @Mock
    private ProfessionalService professionalService;

    @Mock
    private RatingService ratingService;

    @Mock
    private ServiceService serviceService;


    @InjectMocks
    private ProfessionalController professionalController;

    @Mock
    private RatingMapper ratingMapper;

    ProfessionalMapper professionalMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

   /*public void testMockMVC() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(professionalController).build();

        mockMvc.perform(get("/professionals"))
                .andExpect(status().isOk())
                .andExpect(view().name())
    }*/

    /*@BeforeEach
    public void setup() {
        professional = Professional.builder().name("").build();
        professionalDto = ProfessionalDto.builder().name("Ka").cpf("54065949").description("").category("").build();
    }*/

    //@Autowired
    //private ProfessionalController professionalController;

    /*@BeforeEach
    public void setup() {

    }*/

    @Test
    public void listAllProfessionals_whenGetMethod() {

    }

    /*
    @Test
    public void ShouldReturnCreated_WhenCreateProfessional() throws Exception {
        given(professionalService.save(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/professionals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(professionalDto)));

        response.andExpect(MockMvcResultHandlers.status().isCreated()
                .andDo());
    }*/
}
