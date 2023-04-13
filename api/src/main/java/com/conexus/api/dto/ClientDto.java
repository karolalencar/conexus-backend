package com.conexus.api.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientDto {
    private Long id;

    private String name;

    private String email;

    private String cpf;

    private String password;

}
