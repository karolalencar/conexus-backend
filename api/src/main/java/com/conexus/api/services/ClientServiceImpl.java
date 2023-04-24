package com.conexus.api.services;

import com.conexus.api.config.PasswordHasher;
import com.conexus.api.domain.Client;
import com.conexus.api.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Client> findAll() {
        Set<Client> clients = new HashSet<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client save(Client object) {
        return clientRepository.save(object);
    }

    @Override
    public void delete(Client object) {
        clientRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findByEmail(String email) {

        List<Client> client = clientRepository.findByEmail(email);
        return client;
    }

    @Override
    public Client updateByClientId(Long id, Client client) {
        client.setId(id);
        return clientRepository.save(client);
    }

    @Override
    public Client updateByClientIdPatch(Client client, Client updatedClient) {

        updatedClient.setId(client.getId());

        if (updatedClient.getName() == null) {
            updatedClient.setName(client.getName());
        }

        if (updatedClient.getCpf() == null) {
            updatedClient.setCpf(client.getCpf());
        }

        if (updatedClient.getName() == null) {
            updatedClient.setName(client.getName());
        }

        if (updatedClient.getEmail() == null) {
            updatedClient.setEmail(client.getEmail());
        }

        if (updatedClient.getPassword() == null) {
            String hashedPassword = PasswordHasher.hashPassword(client.getPassword());
            updatedClient.setPassword(hashedPassword);
        }

        return clientRepository.save(updatedClient);
    }
}
