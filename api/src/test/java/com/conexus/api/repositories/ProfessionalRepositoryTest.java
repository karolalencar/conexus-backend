package com.conexus.api.repositories;

import com.conexus.api.domain.Professional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProfessionalRepositoryTest {

    @Autowired
    ProfessionalRepository professionalRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllByCategory() {
    }

    @Test
    void findAllByDescription() {
    }
}