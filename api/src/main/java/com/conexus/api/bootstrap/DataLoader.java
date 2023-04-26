package com.conexus.api.bootstrap;

import com.conexus.api.config.PasswordHasher;
import com.conexus.api.domain.*;
import com.conexus.api.repositories.*;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProfessionalRepository professionalRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final RatingRepository ratingRepository;
    private final PaymentRepository paymentRepository;
    private final ScheduleRepository scheduleRepository;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public DataLoader(ProfessionalRepository professionalRepository, ClientRepository clientRepository, ServiceRepository serviceRepository, RatingRepository ratingRepository, PaymentRepository paymentRepository, ScheduleRepository scheduleRepository) {
        this.professionalRepository = professionalRepository;
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
        this.ratingRepository = ratingRepository;
        this.paymentRepository = paymentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void  loadData() {

        Professional professionalK = new Professional();
        professionalK.setName("Profissional K");
        professionalK.setCpf("43593859878");
        professionalK.setCategory("decorator");
        professionalK.setDescription("Decoração de festas");
        professionalK.setEmail("k@gmail.com");
        String professionalHashedPassword = PasswordHasher.hashPassword("1223");
        professionalK.setPassword(professionalHashedPassword);
        professionalRepository.save(professionalK);

        Professional professionalX =
                new Professional(10L, "Professional X", "x@gmail.com", "28947",
                        PasswordHasher.hashPassword("123"), "decorator", "Decoração de eventos");
        professionalRepository.save(professionalX);

        Professional professionalZ =
                new Professional(10L, "Professional Z", "z@gmail.com", "2894778",
                        PasswordHasher.hashPassword("123"), "teacher", "Professor de francês particular");
        professionalRepository.save(professionalZ);

        Client client = new Client();
        client.setName("Cliente K");
        client.setEmail("ck@gmail.com");
        client.setCpf("68542896");
        String clientHashedPassword = PasswordHasher.hashPassword("1223");
        client.setPassword(clientHashedPassword);
        Client savedClient = clientRepository.save(client);

        Payment payment2 = new Payment("constructor", 85.5);
        paymentRepository.save(payment2);
        Payment payment = new Payment();
        payment.setMethod("Credit");
        payment.setAmount(80.0);
        Payment savedPayment = paymentRepository.save(payment);

        Schedule schedule = new Schedule();
        schedule.setStartService(LocalDateTime.of(2023, 04, 24, 12, 40, 0));
        schedule.setEndService(LocalDateTime.of(2023, 04, 24, 14, 20, 0));
        schedule.setProfessional(professionalK);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        Services service = new Services();
        service.setAddress("Rua 2");
        service.setDescription("Serviço x");
        service.setClient(client);
        service.setProfessional(professionalK);
        service.setPayment(payment);
        service.setSchedule(schedule);
        Services savedService = serviceRepository.save(service);

        Rating rating = new Rating();
        rating.setRate(8.9);
        rating.setComment("Executou bem o serviço");
        rating.setProfessional(professionalK);
        rating.setClient(client);
        Rating savedRating = ratingRepository.save(rating);

        Rating rating2 = new Rating();
        rating2.setRate(7.5);
        rating2.setComment("Mais ou menos");
        rating2.setProfessional(professionalK);
        rating2.setClient(client);
        Rating savedRating2 = ratingRepository.save(rating2);
    }
}
