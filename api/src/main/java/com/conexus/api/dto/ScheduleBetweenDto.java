package com.conexus.api.dto;

import com.conexus.api.domain.Professional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScheduleBetweenDto {

    private Long id;

    private LocalDateTime startService;

    private LocalDateTime endService;

    private Professional professional;
}
