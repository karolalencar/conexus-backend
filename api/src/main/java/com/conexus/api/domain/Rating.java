package com.conexus.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

    @Column
    private Double rate;

    @Column
    private String comment;

    @ManyToOne
    private Professional professional;

/*
    @Column
    @OneToOne
    private Client client;*/
}
