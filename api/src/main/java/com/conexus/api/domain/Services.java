package com.conexus.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "services")
public class Services extends BaseEntity{

    public Services(Long id, String address, String description, Client client, Professional professional, Payment payment, Schedule schedule) {
        super(id);
        this.address = address;
        this.description = description;
        this.client = client;
        this.professional = professional;
        this.payment = payment;
        this.schedule = schedule;
    }

    @Column
    private String address;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @OneToOne
    private Payment payment;

    @OneToOne
    private Schedule schedule;
}
