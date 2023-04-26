package com.conexus.api.mappers;

import com.conexus.api.domain.Payment;
import com.conexus.api.dto.PaymentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentMapperTest {

    public static final Long ID = 1L;
    public static final String METHOD = "Credit";
    public static final Double AMOUNT = 10.0;

    @InjectMocks
    private PaymentMapperImpl paymentMapper;

    private Payment payment;

    private PaymentDto paymentDto;

    @BeforeEach
    void setUp() {

        payment = new Payment(ID, METHOD, AMOUNT);
        paymentDto = new PaymentDto(ID, METHOD, AMOUNT);
    }

    @Test
    void testPaymentToPaymentDto() {

        PaymentDto resultPaymentDto = paymentMapper.paymentToPaymentDto(payment);

        assertEquals(ID, resultPaymentDto.getId());
        assertEquals(METHOD, resultPaymentDto.getMethod());
        assertEquals(AMOUNT, resultPaymentDto.getAmount());
    }

    @Test
    void testPaymentDtoToPayment() {

        Payment resultPayment = paymentMapper.paymentDtoToPayment(paymentDto);

        assertEquals(ID, resultPayment.getId());
        assertEquals(METHOD, resultPayment.getMethod());
        assertEquals(AMOUNT, resultPayment.getAmount());
    }
}