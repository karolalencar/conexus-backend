package com.conexus.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
//@Setter
@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "professionals")
public class Professional extends User {

    public Professional() {
    }

    public Professional(String name, String email, String cpf, String password, String category, String description) {
        super(name, email, cpf, password);
        this.category = category;
        this.description = description;
    }



    @Column
    private String category;

    @Column
    private String description;

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
