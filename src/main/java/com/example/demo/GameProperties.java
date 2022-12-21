package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "game")
@Getter
@Setter
@Validated
public class GameProperties {

    @NotNull
    private String minBet;

    @NotNull
    private String maxBet;

    @NotNull
    private final Map<String, String> wins = new HashMap<>();

}
