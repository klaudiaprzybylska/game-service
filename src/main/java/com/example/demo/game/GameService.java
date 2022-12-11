package com.example.demo.game;

import com.example.demo.dto.InputDto;
import org.springframework.stereotype.Component;

@Component
public class GameService {

    private final GameServiceStrategyFactory factory;

    public GameService(GameServiceStrategyFactory factory) {
        this.factory = factory;
    }

    public Game play(InputDto inputDto) {
        GameServiceStrategy strategy = factory.getStrategy(inputDto.getGameType());

        return strategy.playGame(inputDto);
    }
}
