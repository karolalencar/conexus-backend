package com.conexus.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class User extends BaseEntity {


    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String cpf;

    @Column
    private String password;
}
