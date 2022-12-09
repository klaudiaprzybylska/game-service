package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameServiceStrategyFactory {

    private final List<GameServiceStrategy> allStrategies;

    public GameServiceStrategyFactory(List<GameServiceStrategy> allStrategies) {
        this.allStrategies = allStrategies;
    }

    public GameServiceStrategy getStrategy(GameType gameType) {
        for (GameServiceStrategy strategy : allStrategies) {
            if (strategy.getType().equals(gameType)) {
                return strategy;
            }
        }
        throw new IllegalStateException("Nieznana strategia");
    }
}
