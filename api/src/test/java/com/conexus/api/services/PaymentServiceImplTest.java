package com.conexus.api.services;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Payment;
import com.conexus.api.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

        when(paymentRepository.save(any())).thenReturn(payment);

        Payment response = paymentService.save(payment);

        assertNotNull(response);
        assertEquals(Payment.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(METHOD, response.getMethod());
        assertEquals(AMOUNT, response.getAmount());
    }

    @Test
    void testDelete() {

        paymentService.delete(payment);

        verify(paymentRepository, times(1)).delete(payment);
    }

    @Test
    void testDeleteById() {

        paymentService.deleteById(ID);

        verify(paymentRepository, times(1)).deleteById(ID);
    }

    @Test
    void testUpdateByPaymentId() {

        when(paymentRepository.save(any())).thenReturn(payment);

        Payment response = paymentService.updateByPaymentId(ID, payment);

        assertNotNull(response);
        assertEquals(Payment.class, response.getClass());
        assertEquals(payment, response);

        verify(paymentRepository, times(1)).save(payment);
    }
}