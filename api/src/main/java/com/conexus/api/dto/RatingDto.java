package com.conexus.api.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

    private Long id;

    private Double rate;

    private String comment;
}
