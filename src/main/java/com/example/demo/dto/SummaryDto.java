package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummaryDto {
    private Integer newBalance;
    private Boolean isFreeRoundWon;
    private Integer prize;
}
