package com.example.demo.game;

import com.example.demo.dto.InputDto;
import com.example.demo.player.Player;
import com.example.demo.player.PlayerService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FreeGame implements GameServiceStrategy {

    private final GameRepository gameRepository;

    private final PlayerService playerService;

    public FreeGame(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }

    @Override
    public GameType getType() {
        return GameType.FREE;
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
        return gameRepository.save(GameEngine.createGame(input.getBet(), true, player));
    }

    @Override
    public void updateBalance(Player player, Game game) {
        BigDecimal balance = GameEngine.updateBalance(player.getBalance(), game, getType());
        boolean isNextGameFree = game.getIsFreeRoundWon();
        playerService.updateAfterGame(balance, isNextGameFree, player.getId());
    }

}
