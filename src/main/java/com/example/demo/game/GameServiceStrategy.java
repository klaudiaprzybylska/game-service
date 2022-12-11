package com.example.demo.game;

import com.example.demo.dto.InputDto;
import com.example.demo.player.Player;

public interface GameServiceStrategy {

    Game playGame(InputDto input);

    Game createGame(InputDto input, Player player);

    void updateBalance(Player player, Game game);

    GameType getType();

}
