package com.example.demo;

import com.example.demo.dto.InputDto;
import org.springframework.stereotype.Component;

@Component
public class GameService {

    private GameServiceStrategy strategy;

    private final RegularGame regularGame;

    public GameService(RegularGame regularGame) {
        this.regularGame = regularGame;
    }

    Game play(InputDto inputDto) {
        if (inputDto.getGameType().equals(GameType.REGULAR)) {
            this.strategy = regularGame;
        }
        return strategy.playGame(inputDto);
    }
}
