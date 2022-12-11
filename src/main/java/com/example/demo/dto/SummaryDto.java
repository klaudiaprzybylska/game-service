package com.example.demo.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummaryDto {

    private BigDecimal balance;

    private Boolean isFreeRoundWon;

    private BigDecimal prize;
}
