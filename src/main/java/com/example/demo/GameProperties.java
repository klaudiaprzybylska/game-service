package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "game")
@Getter
@Setter
public class GameProperties {

    private String minBet;

    private String maxBet;

    private final Map<String, String> wins = new HashMap<>();

}
