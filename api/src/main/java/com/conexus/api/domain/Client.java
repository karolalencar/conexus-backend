package com.conexus.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
// @AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends User {

    private String temp;

    public Client() {
    }

    public Client(String name, String email, String cpf, String password, String temp) {
        super(name, email, cpf, password);
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
