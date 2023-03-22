package com.conexus.api.controllers;

import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.mappers.ProfessionalMapper;
import com.conexus.api.services.ProfessionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Profissionais")
@RequestMapping("professionals")
public class ProfessionalController {

    private final ProfessionalMapper professionalMapper;
    private final ProfessionalService professionalService;


    public ProfessionalController(ProfessionalMapper professionalMapper, ProfessionalService professionalService) {
        this.professionalMapper = professionalMapper;
        this.professionalService = professionalService;
    }
    @Operation(summary = "Retorna a lista de todos os profissionais")
    @GetMapping("")
    public List<ProfessionalDto> getProfessionals() {
        /*System.out.println("Entrou!!!!!!!!!!!!!!!!!!!!!");
        Set<Professional> professionals = professionalService.findAll();
        System.out.println(professionals);
        System.out.println("Aqui!!!!!!!!!!!********");
        return professionals.stream()
                .map(professionalMapper::professionalToProfessionalDto)
                .collect(Collectors.toList());*/
        List<ProfessionalDto> professionals = professionalService.findAll()
                .stream()
                .map(professionalMapper::professionalToProfessionalDto)
                .collect(Collectors.toList());
        return professionals;
    }



    @GetMapping("/{id}")
    public ProfessionalDto getProfessional(@PathVariable Long id) {
        System.out.println("Aquiiiiiiiiiiiii");
        Professional professional = professionalService.findById(id);
        System.out.println(professional);
        ProfessionalDto professionalDto = professionalMapper.professionalToProfessionalDto(professional);
        return professionalDto;
    }

    @GetMapping("/register")
    public String registerProfessional() {
        return "professionals/registerForm";
    }

    @PostMapping("/save")
    public String saveNewProfessional() {


        return "redirect:/professionals/list";
    }
}
