package com.conexus.api.controllers;

import com.conexus.api.config.PasswordHasher;
import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Services;
import com.conexus.api.dto.ClientDto;
import com.conexus.api.dto.ServiceDto;
import com.conexus.api.mappers.ClientMapper;
import com.conexus.api.mappers.ServiceMapper;
import com.conexus.api.services.ClientService;
import com.conexus.api.services.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Clientes")
@RequestMapping("/clients")
public class ClientController {

    private  final ClientMapper clientMapper;
    private final ClientService clientService;
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;

    public ClientController(ClientMapper clientMapper, ClientService clientService, ServiceService serviceService, ServiceMapper serviceMapper) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
        this.serviceService = serviceService;
        this.serviceMapper = serviceMapper;
    }

    @Operation(summary = "Cria um novo cliente")
    @PostMapping("")
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDto clientDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        if (!clientService.findByEmail(clientDto.getEmail()).isEmpty()) {
            return new ResponseEntity<>("E-mail already registered", HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = PasswordHasher.hashPassword(clientDto.getPassword());
        clientDto.setPassword(hashedPassword);

        Client client = clientMapper.clientDtoToClient(clientDto);
        clientService.save(client);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna a lista de todos os clientes")
    @GetMapping("")
    public ResponseEntity<List<ClientDto>> getClients() {
        List<ClientDto> clients = clientService.findAll()
                .stream()
                .map(clientMapper::clientToClientDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um cliente pelo id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientMapper.clientToClientDto(client), HttpStatus.OK);
    }

    @Operation(summary = "Retorna todos os serviços contratados por um cliente pelo id")
    @GetMapping("/{id}/services")
    public ResponseEntity<List<ServiceDto>> getServicesByClient(@PathVariable Long id) {
        List<ServiceDto> services = serviceService.findAllByClientId(id)
                .stream()
                .map(serviceMapper::serviceToServiceDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Atualiza um cliente pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Client client = clientService.findById(id);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        String hashedPassword = PasswordHasher.hashPassword(clientDto.getPassword());
        clientDto.setPassword(hashedPassword);

        Client updatedClient = clientService.updateByClientId(id, client);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("").buildAndExpand(updatedClient.getId()).toUri();
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um profissional pelo id")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClientPatch(@PathVariable Long id, @RequestBody ClientDto clientDto) {

        Client client = clientService.findById(id);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        Client updatedClient = clientMapper.clientDtoToClient(clientDto);
        updatedClient = clientService.updateByClientIdPatch(client, updatedClient);
        return new ResponseEntity<>(clientMapper.clientToClientDto(updatedClient), HttpStatus.CREATED);
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
