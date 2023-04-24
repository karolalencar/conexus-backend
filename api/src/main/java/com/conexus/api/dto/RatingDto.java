package com.conexus.api.dto;

import com.conexus.api.domain.Professional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RatingDto {

    private Long id;

    @NotNull(message = "Rate is mandatory")
    private Double rate;

    private String comment;

    @NotNull(message = "Professional id is mandatory")
    private Long professionalId;

    @NotNull(message = "Client id is mandatory")
    private Long clientId;
}
