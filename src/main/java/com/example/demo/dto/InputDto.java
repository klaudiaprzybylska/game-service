package com.example.demo.dto;

import com.example.demo.CorrectBetRange;
import com.example.demo.game.GameType;
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

    @CorrectBetRange
    private BigDecimal bet;

    private Long playerId;

    private GameType gameType;
}
