package com.conexus.api.dto;

import com.conexus.api.domain.Professional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RatingBetweenDto {

    private Long id;

    private Double rate;

    private String comment;

    private Professional professional;
}
