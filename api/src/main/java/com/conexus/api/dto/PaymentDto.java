package com.conexus.api.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;

    private String method;

    private Double amount;
}
