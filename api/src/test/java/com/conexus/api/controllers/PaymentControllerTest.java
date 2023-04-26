package com.conexus.api.controllers;

import com.conexus.api.domain.Payment;
import com.conexus.api.mappers.PaymentMapper;
import com.conexus.api.repositories.PaymentRepository;
import com.conexus.api.services.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    public static final Long ID = 1L;
    public static final String METHOD = "Credit";
    public static final Double AMOUNT = 50.0;

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentMapper paymentMapper;

    private Payment payment;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createPayment() {
    }

    @Test
    void getPayment() {
    }

    @Test
    void updatePayment() {
    }
}