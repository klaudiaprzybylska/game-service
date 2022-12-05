package com.example.demo;

import com.example.demo.dto.InputDto;

public interface GameServiceStrategy {

    Game playGame(InputDto input);

    Game createGame(InputDto input, Player player);

    Player getPlayerById(Long playerId);

    // todo validate if player has enough money to make bet

    void updateBalance(Player player, Game game);


}
