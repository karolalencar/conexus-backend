package com.conexus.api.dto;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceDto {

    private Long id;

    private String Address;

    private String description;

    private LocalDate date;

    private Client client;

    private Professional professional;
}
