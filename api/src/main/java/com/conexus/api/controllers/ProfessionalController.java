package com.conexus.api.controllers;

import com.conexus.api.services.ProfessionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Profissionais")
@RequestMapping("professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @Operation(summary = "Retorna a lista de todos os profissionais")
    @GetMapping("")
    public String getProfessionals() {
        return "professionals/list";
    }



    @GetMapping("/{id}")
    public String getProfessional(@PathVariable("id") Long id, Model model) {
        model.addAttribute("professional", professionalService.findById(id));

        return "professionals/list";
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
