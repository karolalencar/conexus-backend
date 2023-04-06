package com.conexus.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "professionals")
public class Professional extends User {

    @Column
    private String category;

    @Column
    private String description;

    @OneToMany(mappedBy = "professional")
    private List<Rating> ratings;

    @ManyToOne
    private Client client;

}
