package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;

import java.util.List;
import java.util.Optional;

public interface ClientService extends CrudService<Client, Long> {

    List<Client> findByEmail(String email);

    Client updateByClientId(Long id, Client client);

    Client updateByClientIdPatch(Client client, Client updatedClient);
}
