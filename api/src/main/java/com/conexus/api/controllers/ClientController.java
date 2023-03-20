package com.conexus.api.controllers;

import com.conexus.api.repositories.ClientRepository;
import com.conexus.api.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Clientes")
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Retorna a lista de todos os clientes")
    @GetMapping("")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.findAll());

        return "clients/index";
    }

    @Operation(summary = "Retorna um cliente pelo id")
    @GetMapping("/{id}")
    public String getClient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));

        return "clients";
    }

}
