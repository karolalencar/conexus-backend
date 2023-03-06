package com.conexus.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
