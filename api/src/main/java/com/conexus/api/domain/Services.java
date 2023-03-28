package com.conexus.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
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

    public Services() {
    }

    public Services(String address, String description, LocalDate date, Client client, Professional professional) {
        Address = address;
        this.description = description;
        this.date = date;
        this.client = client;
        this.professional = professional;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }
}
