package com.conexus.api.dto;

import com.conexus.api.domain.Client;
import com.conexus.api.domain.Professional;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ServiceDto {

    private Long id;

    @NotNull(message = "Address is mandatory")
    private String address;

    private String description;

    @NotNull(message = "Client id is mandatory")
    private Long clientId;

    @NotNull(message = "Professional id is mandatory")
    private Long professionalId;

    @NotNull(message = "Payment id is mandatory")
    private Long paymentId;

    @NotNull(message = "Schedule id is mandatory")
    private Long scheduleId;
}
