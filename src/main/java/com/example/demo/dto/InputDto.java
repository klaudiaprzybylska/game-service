package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputDto {
    private int bet;
    private Long playerId;
    @JsonProperty(value = "isFree")
    private boolean isFree;
}
