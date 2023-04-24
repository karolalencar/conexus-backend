package com.conexus.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@MappedSuperclass
public class User extends BaseEntity {

    public User(Long id, String name, String email, String cpf, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }



    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String cpf;

    @Column
    private String password;
}
