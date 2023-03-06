package com.conexus.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "professionals")
public class Professional extends User {

    public Professional(Long id, String name, String email, String cpf, String password, String category) {
        super(id, name, email, cpf, password);
        this.category = category;
    }

    @Column
    private String category;

}
