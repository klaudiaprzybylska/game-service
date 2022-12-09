package com.example.demo;

import com.example.demo.dto.InputDto;

public interface GameServiceStrategy {

    Game playGame(InputDto input);

    Game createGame(InputDto input, Player player);

    // todo validate if player has enough money to make a bet

    void updateBalance(Player player, Game game);

    GameType getType();

}
