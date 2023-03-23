package com.conexus.api.dto;

import jakarta.persistence.Column;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessionalDto {

    private Long id;

    private String name;

    private String email;

    private String cpf;

    private String password;

    private String category;

    private String description;
}
