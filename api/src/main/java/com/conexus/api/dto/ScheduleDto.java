package com.conexus.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScheduleDto {

    private Long id;

    @NotNull(message = "Start date is mandatory")
    private LocalDateTime startService;

    @NotNull(message = "End date is mandatory")
    private LocalDateTime endService;

    @NotNull(message = "Professional id is mandatory")
    private Long professionalId;
}
