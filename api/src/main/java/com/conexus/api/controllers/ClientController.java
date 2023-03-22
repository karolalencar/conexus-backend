package com.conexus.api.controllers;

import com.conexus.api.domain.Client;
import com.conexus.api.dto.ClientDto;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.mappers.ClientMapper;
import com.conexus.api.repositories.ClientRepository;
import com.conexus.api.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Clientes")
@RequestMapping("/clients")
public class ClientController {

    private  final ClientMapper clientMapper;
    private final ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @Operation(summary = "Retorna a lista de todos os clientes")
    @GetMapping("")
    public List<ClientDto> getClients() {
        List<ClientDto> clients = clientService.findAll()
                .stream()
                .map(clientMapper::clientToClientDto)
                .collect(Collectors.toList());
        return clients;
    }

    @Operation(summary = "Retorna um cliente pelo id")
    @GetMapping("/{id}")
    public String getClient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));

        return "clients";
    }

}
