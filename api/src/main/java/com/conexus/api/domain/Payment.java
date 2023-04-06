package com.conexus.api.domain;

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

    @Column
    private String method;

    @Column
    private Double amount;

    @OneToOne
    private Services service;
}
