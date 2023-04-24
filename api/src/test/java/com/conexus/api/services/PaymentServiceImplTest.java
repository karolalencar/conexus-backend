package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Payment;
import com.conexus.api.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class PaymentServiceImplTest {

    public static final Long ID = 1L;
    public static final String METHOD = "Credit";
    public static final double AMOUNT = 89.5;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment;

    @BeforeEach
    void setUp() {

        payment = new Payment(ID, METHOD, AMOUNT);
    }

    @Test
    void testFindAll() {

        when(paymentRepository.findAll()).thenReturn(List.of(payment));

        Set<Payment> response = paymentService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testFindById() {

        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(payment));

        Payment response = paymentService.findById(ID);

        assertNotNull(response);

        assertEquals(Payment.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(METHOD, response.getMethod());
        assertEquals(AMOUNT, response.getAmount());
    }

    @Test
    void testSave() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testDeleteById() {
    }

    @Test
    void testUpdateByPaymentId() {
    }
}