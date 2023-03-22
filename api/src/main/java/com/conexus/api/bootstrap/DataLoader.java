package com.conexus.api.bootstrap;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.repositories.ClientRepository;
import com.conexus.api.repositories.ProfessionalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProfessionalRepository professionalRepository;

    private final ClientRepository clientRepository;

    public DataLoader(ProfessionalRepository professionalRepository, ClientRepository clientRepository) {
        this.professionalRepository = professionalRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void  loadData() {
        Professional professional = new Professional();
        professional.setName("Kar");
        professional.setCpf("43593859878");
        professional.setCategory("fotográfo");
        professional.setDescription("la ala lal");
        professional.setEmail("dfsaj@gfkcjdgk.com");
        professional.setPassword("fjsdklgj");
        System.out.println(professional.getName());
        Professional savedProfessional = professionalRepository.save(professional);

        Client client = new Client();
        client.setName("Karo");
        client.setEmail("karo@gmail.com");
        client.setCpf("68542896");
        client.setPassword("123");
        client.setTemp("jhghl");
        System.out.println(client.getName());
        Client savedClient = clientRepository.save(client);
    }
}
