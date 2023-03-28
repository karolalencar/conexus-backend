package com.conexus.api.bootstrap;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import com.conexus.api.domain.Rating;
import com.conexus.api.domain.Services;
import com.conexus.api.repositories.ClientRepository;
import com.conexus.api.repositories.ProfessionalRepository;
import com.conexus.api.repositories.RatingRepository;
import com.conexus.api.repositories.ServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProfessionalRepository professionalRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final RatingRepository ratingRepository;

    public DataLoader(ProfessionalRepository professionalRepository, ClientRepository clientRepository, ServiceRepository serviceRepository, RatingRepository ratingRepository) {
        this.professionalRepository = professionalRepository;
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
        this.ratingRepository = ratingRepository;
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
        Professional savedProfessional = professionalRepository.save(professional);

        Client client = new Client();
        client.setName("Karo");
        client.setEmail("karo@gmail.com");
        client.setCpf("68542896");
        client.setPassword("123");
        client.setTemp("jhghl");
        Client savedClient = clientRepository.save(client);

        Services service = new Services();
        service.setAddress("Rua 2");
        service.setDescription("Serviço x");
        service.setDate(LocalDate.now());
        service.setClient(client);
        service.setProfessional(professional);
        service.setDescription("fkjslk");
        Services savedService = serviceRepository.save(service);

        Rating rating = new Rating();
        rating.setRate(8.9);
        rating.setComment("Executou bem o serviço");
        rating.setProfessional(professional);
        Rating savedRating = ratingRepository.save(rating);
    }
}
