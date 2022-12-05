package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummaryDto {

    private float balance;

    private Boolean isFreeRoundWon;

    private int prize;
}
