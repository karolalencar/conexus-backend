package com.conexus.api.services;

import com.conexus.api.domain.Payment;

public interface PaymentService extends CrudService<Payment, Long> {

    Payment updateByPaymentId(Long id, Payment payment);
}
