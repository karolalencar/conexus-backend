package com.conexus.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientDto {
    private Long id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Date is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    private String cpf;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password is mandatory")
    private String password;

}
