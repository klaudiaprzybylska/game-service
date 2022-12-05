package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {

    private Long playerId;

    private String name;

    private float balance;

    private boolean isNextFree;
}
