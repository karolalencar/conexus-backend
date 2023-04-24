package com.conexus.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "professionals")
public class Professional extends User {

    public Professional(Long id, String name, String email, String cpf, String password, String category, String description) {
        super(id, name, email, cpf, password);
        this.category = category;
        this.description = description;
    }

    @Column
    private String category;

    @Column
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @JsonIgnore
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

}
