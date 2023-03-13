package com.conexus.api.services.impl;

import com.conexus.api.domain.Payment;
import com.conexus.api.repositories.PaymentRepository;
import com.conexus.api.services.PaymentService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public Set<Payment> findAll() {
        Set<Payment> payments = new HashSet<>();
        paymentRepository.findAll().forEach(payments::add);
        return payments;
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment save(Payment object) {
        return paymentRepository.save(object);
    }

    @Override
    public void delete(Payment object) {
        paymentRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }
}
