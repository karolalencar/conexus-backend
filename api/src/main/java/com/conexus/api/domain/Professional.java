package com.conexus.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professionals")
public class Professional extends User {

    @Column
    private String category;

    @Column
    private String description;

    @OneToMany(mappedBy = "professional")
    private List<Rating> ratings;
}
