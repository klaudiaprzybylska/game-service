package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "game")
@Getter
@Setter
@Validated
public class GameProperties {

    @NotNull
    private BigDecimal minBet;

    @NotNull
    private BigDecimal maxBet;

    @NotNull
    private final Map<Integer, BigDecimal> wins = new HashMap<>();

}
