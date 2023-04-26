package com.conexus.api.mappers;

import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ProfessionalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfessionalMapperTest {

    public static final Long ID = 1L;
    public static final String NAME = "TestName";
    public static final String EMAIL = "test@email.com";
    public static final String CPF = "0123456789";
    public static final String PASSWORD = "password";
    public static final String CATEGORY = "category";
    public static final String DESCRIPTION = "description";

    @InjectMocks
    private ProfessionalMapperImpl professionalMapper;

    private Professional professional;

    private ProfessionalDto professionalDto;

    @BeforeEach
    void setUp() {

        professional = new Professional(ID, NAME, EMAIL, CPF, PASSWORD, CATEGORY, DESCRIPTION);
        professionalDto = new ProfessionalDto(ID, NAME, EMAIL, CPF, PASSWORD, CATEGORY, DESCRIPTION);
    }

    @Test
    void testProfessionalToProfessionalDto() {

        ProfessionalDto resultProfessionalDto = professionalMapper.professionalToProfessionalDto(professional);

        assertEquals(ID, resultProfessionalDto.getId());
        assertEquals(NAME, resultProfessionalDto.getName());
        assertEquals(EMAIL, resultProfessionalDto.getEmail());
        assertEquals(CPF, resultProfessionalDto.getCpf());
        assertEquals(PASSWORD, resultProfessionalDto.getPassword());
        assertEquals(CATEGORY, resultProfessionalDto.getCategory());
        assertEquals(DESCRIPTION, resultProfessionalDto.getDescription());
    }

    @Test
    void testProfessionalDtoToProfessional() {

        Professional resultProfessional = professionalMapper.professionalDtoToProfessional(professionalDto);
        
        assertEquals(NAME, resultProfessional.getName());
        assertEquals(EMAIL, resultProfessional.getEmail());
        assertEquals(CPF, resultProfessional.getCpf());
        assertEquals(PASSWORD, resultProfessional.getPassword());
        assertEquals(CATEGORY, resultProfessional.getCategory());
        assertEquals(DESCRIPTION, resultProfessional.getDescription());
    }

}