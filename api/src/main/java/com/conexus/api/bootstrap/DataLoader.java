package com.conexus.api.bootstrap;

import com.conexus.api.domain.*;
import com.conexus.api.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProfessionalRepository professionalRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final RatingRepository ratingRepository;
    private final PaymentRepository paymentRepository;

    public DataLoader(ProfessionalRepository professionalRepository, ClientRepository clientRepository, ServiceRepository serviceRepository, RatingRepository ratingRepository, PaymentRepository paymentRepository) {
        this.professionalRepository = professionalRepository;
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
        this.ratingRepository = ratingRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void  loadData() {

        Professional professional = new Professional();
        professional.setName("Kar");
        professional.setCpf("43593859878");
        professional.setCategory("decorator");
        professional.setDescription("la ala lal");
        professional.setEmail("dfsaj@gfkcjdgk.com");
        professional.setPassword("fjsdklgj");
        Professional savedProfessional = professionalRepository.save(professional);

        Client client = new Client();
        client.setName("Karo");
        client.setEmail("karo@gmail.com");
        client.setCpf("68542896");
        client.setPassword("123");
        Client savedClient = clientRepository.save(client);

        Payment payment = new Payment();
        payment.setMethod("Credit");
        payment.setAmount(80.0);
        Payment savedPayment = paymentRepository.save(payment);

        Services service = new Services();
        service.setAddress("Rua 2");
        service.setDescription("Serviço x");
        service.setDate(LocalDate.now());
        service.setClient(client);
        service.setProfessional(professional);
        service.setPayment(payment);
        Services savedService = serviceRepository.save(service);

        Rating rating = new Rating();
        rating.setRate(8.9);
        rating.setComment("Executou bem o serviço");
        rating.setProfessional(professional);
        Rating savedRating = ratingRepository.save(rating);

        Rating rating2 = new Rating();
        rating2.setRate(7.5);
        rating2.setComment("Mais ou menos");
        rating2.setProfessional(professional);
        Rating savedRating2 = ratingRepository.save(rating2);
    }
}
