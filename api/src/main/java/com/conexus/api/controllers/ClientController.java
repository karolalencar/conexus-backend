package com.conexus.api.controllers;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.dto.ClientDto;
import com.conexus.api.dto.ProfessionalDto;
import com.conexus.api.mappers.ClientMapper;
import com.conexus.api.repositories.ClientRepository;
import com.conexus.api.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClient(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return clientMapper.clientToClientDto(client);
    }

    @Operation(summary = "Deleta um cliente pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        clientService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
