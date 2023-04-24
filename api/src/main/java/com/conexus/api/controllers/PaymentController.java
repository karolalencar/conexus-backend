package com.conexus.api.controllers;

import com.conexus.api.domain.Payment;
import com.conexus.api.dto.PaymentDto;
import com.conexus.api.mappers.PaymentMapper;
import com.conexus.api.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Pagamentos")
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @Operation(summary = "Cria uma avaliação")
    @PostMapping("")
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentDto paymentDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Payment payment = paymentMapper.paymentDtoToPayment(paymentDto);
        Payment newPayment = paymentService.save(payment);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna a lista de pagamentos")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        PaymentDto paymentDtoDto = paymentMapper.paymetToPaymentDto(payment);
        return new ResponseEntity<>(paymentDtoDto, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um pagamento pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDto paymentDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Payment payment = paymentService.findById(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }

        payment = paymentMapper.paymentDtoToPayment(paymentDto);
        Payment updatedPayment = paymentService.updateByPaymentId(id, payment);
        return new ResponseEntity<>(paymentMapper.paymetToPaymentDto(updatedPayment), HttpStatus.CREATED);
    }
}
