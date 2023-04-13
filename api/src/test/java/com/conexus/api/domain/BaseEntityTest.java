package com.conexus.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    private BaseEntity baseEntity;

    @BeforeEach
    public void setUp() {
        baseEntity = new BaseEntity();
    }


    @Test
    void getId() {
        baseEntity.setId(1L);

        assertEquals(1L, baseEntity.getId());
    }

    @Test
    void setId() {
    }
}