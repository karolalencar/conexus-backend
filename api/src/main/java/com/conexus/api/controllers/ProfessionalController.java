package com.conexus.api.controllers;

import com.conexus.api.services.ProfessionalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping({"", "/"})
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
