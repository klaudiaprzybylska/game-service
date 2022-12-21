package com.example.demo.game;

import com.example.demo.dto.InputDto;
import com.example.demo.player.Player;
import com.example.demo.player.PlayerService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RegularGame implements GameServiceStrategy {

    private final GameRepository gameRepository;

    private final PlayerService playerService;

    private final GameEngine gameEngine;

    private static final GameType type = GameType.REGULAR;

    public RegularGame(GameRepository gameRepository, PlayerService playerService, GameEngine gameEngine) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.gameEngine = gameEngine;
    }

    @Override
    public GameType getType() {
        return type;
    }

    @Override
    public Game playGame(InputDto input) {
        Player player = playerService.getPlayerById(input.getPlayerId());
        Game game = createGame(input, player);
        updateBalance(player, game);

        return game;
    }

    @Override
    public Game createGame(InputDto input, Player player) {
        return gameRepository.save(gameEngine.createGame(input.getBet(), false, player));
    }

    @Override
    public void updateBalance(Player player, Game game) {
        BigDecimal balance = gameEngine.updateBalance(player.getBalance(), game, getType());
        boolean isNextGameFree = game.getIsFreeRoundWon();
        playerService.updateAfterGame(balance, isNextGameFree, player.getId());
    }

}

