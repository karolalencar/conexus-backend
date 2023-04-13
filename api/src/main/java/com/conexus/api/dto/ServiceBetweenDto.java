package com.conexus.api.dto;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Payment;
import com.conexus.api.domain.Professional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ServiceBetweenDto {

    private Long id;

    private String address;

    private String description;

    private LocalDate date;

    private Client client;

    private Professional professional;

    private Payment payment;
}
