package com.conexus.api.repositories;

import com.conexus.api.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
