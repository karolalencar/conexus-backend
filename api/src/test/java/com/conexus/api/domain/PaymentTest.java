package com.conexus.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentTest {

    public static final Long ID = 1L;
    public static final String METHOD = "TestMethod";
    public static final Double AMOUNT = 80.0;

    @InjectMocks
    private Payment payment;

    @BeforeEach
    void setUp() {

        payment = new Payment(ID, METHOD, AMOUNT);
    }

    @Test
    void testSetAndGetMethod() {

        Payment newPayment = new Payment();
        newPayment.setMethod(METHOD);

        assertEquals(METHOD, newPayment.getMethod());
    }

    @Test
    void testSetAndGetAmount() {

        Payment newPayment = new Payment();
        newPayment.setAmount(AMOUNT);

        assertEquals(AMOUNT, newPayment.getAmount());
    }

    @Test
    void testEquals() {

        Payment payment1 = new Payment(ID, METHOD, AMOUNT);
        Payment payment2 = new Payment(ID, METHOD, AMOUNT);
        Payment payment3 = new Payment(2L, METHOD, 59.7);

        assertEquals(payment1, payment2);
        assertNotEquals(payment2, payment3);
    }

    @Test
    void testCanEqual() {

        Payment payment1 = new Payment(ID, METHOD, AMOUNT);
        Payment payment2 = new Payment(ID, METHOD, AMOUNT);

        assertTrue(payment1.canEqual(payment2));
    }

    @Test
    void testHashCode() {

        Payment payment1 = new Payment(ID, METHOD, AMOUNT);
        Payment payment2 = new Payment(ID, METHOD, AMOUNT);

        assertEquals(payment1.hashCode(), payment2.hashCode());
    }

    @Test
    void testToString() {

        String expectedString = "Payment(method=TestMethod, amount=80.0)";

        assertEquals(expectedString, payment.toString());
    }
}