package com.example.demo.dto;

import com.example.demo.GameType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputDto {

    @Min(1)
    @Max(10)
    private float bet;

    private Long playerId;

    private GameType gameType;
}
