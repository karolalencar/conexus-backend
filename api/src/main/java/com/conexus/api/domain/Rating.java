package com.conexus.api.domain;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

    public Rating(Long id, Double rate, String comment, Professional professional, Client client) {
        super(id);
        this.rate = rate;
        this.comment = comment;
        this.professional = professional;
        this.client = client;
    }

    @Column
    private Double rate;

    @Column
    private String comment;

    @ManyToOne
    private Professional professional;

    @ManyToOne
    private Client client;
}
