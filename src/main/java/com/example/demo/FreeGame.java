package com.example.demo;

import com.example.demo.dto.InputDto;
import org.springframework.stereotype.Component;

@Component
public class FreeGame implements GameServiceStrategy {


    @Override
    public Game playGame(InputDto input) {
        return null;
    }

    @Override
    public Game createGame(InputDto input, Player player) {
        return null;
    }

    @Override
    public void updateBalance(Player player, Game game) {

    }

    @Override
    public GameType getType() {
        return GameType.FREE;
    }
}
