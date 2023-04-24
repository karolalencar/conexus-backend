package com.conexus.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    public static final long ID = 1L;
    public static final String NAME = "TestName";
    public static final String EMAIL = "test@gmail.com";
    public static final String CPF = "4893266";
    public static final String PASSWORD = "123";
    private User user;

    @BeforeEach
    void setUp() {

        user = new User(ID, NAME, EMAIL, CPF, PASSWORD);
    }

    @Test
    public void testUser() {

        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(CPF, user.getCpf());
        assertEquals(PASSWORD, user.getPassword());
    }

    @Test
    public void testBaseEntity() {

        User user1 = new User();
        assertNull(user1.getId());
        user1.setId(ID);
        assertEquals(ID, user1.getId());
    }
}