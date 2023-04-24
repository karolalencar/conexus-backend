package com.conexus.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentDto {

    private Long id;

    @NotNull(message = "Payment method is mandatory")
    private String method;

    @NotNull(message = "Payment amount is mandatory")
    private Double amount;
}
