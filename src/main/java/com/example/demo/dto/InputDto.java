package com.example.demo.dto;

import com.example.demo.GameType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputDto {

    @Min(1)
    @Max(10)
    private BigDecimal bet;

    private Long playerId;

    private GameType gameType;
}
