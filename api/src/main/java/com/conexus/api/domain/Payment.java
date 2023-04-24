package com.conexus.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    public Payment(Long id, String method, Double amount) {
        super(id);
        this.method = method;
        this.amount = amount;
    }

    @Column
    private String method;

    @Column
    private Double amount;

    /*@JsonIgnore
    @OneToOne
    private Services service;*/
}
