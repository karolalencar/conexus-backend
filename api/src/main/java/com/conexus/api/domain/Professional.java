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

    @Column
    private String category;

    @Column
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<Rating> ratings;

}
