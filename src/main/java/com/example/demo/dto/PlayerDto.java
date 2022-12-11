package com.example.demo.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {

    private Long playerId;

    private String name;

    private BigDecimal balance;

    private boolean isNextFree;
}
