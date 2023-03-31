package com.conexus.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "services")
public class Services extends BaseEntity{

    @Column
    private String Address;

    @Column
    private String description;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @OneToOne
    private Payment payment;
}
