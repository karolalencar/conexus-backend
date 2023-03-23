package com.conexus.api.dto;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class ServiceDto {

    private Long id;

    private String Address;

    private String description;

    private LocalDate date;

    private Client client;

    private Professional professional;

    public ServiceDto() {
    }

    public ServiceDto(Long id, String address, String description, LocalDate date, Client client, Professional professional) {
        this.id = id;
        Address = address;
        this.description = description;
        this.date = date;
        this.client = client;
        this.professional = professional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
