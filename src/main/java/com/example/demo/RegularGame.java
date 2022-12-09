package com.example.demo;

import com.example.demo.dto.InputDto;
import org.springframework.stereotype.Component;

import static com.example.demo.GameEngine.playGameForCash;
import static com.example.demo.GameEngine.playGameForFreeRound;

@Component
public class RegularGame implements GameServiceStrategy {

    private final GameRepository gameRepository;

    private final PlayerService playerService;

    private final GameType type = GameType.REGULAR;

    public RegularGame(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
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
        Game game = Game.builder()
                .bet(input.getBet())
                .prize(playGameForCash(input.getBet()))
                .player(player)
                .isFree(false)
                .isFreeRoundWon(playGameForFreeRound())
                .build();
        return gameRepository.save(game);
    }

    @Override
    public void updateBalance(Player player, Game game) {
        float balance = player.getBalance();
        float prize = game.getPrize();
        balance += prize;
        balance -= game.getBet();
        boolean isNextGameFree = game.getIsFreeRoundWon();
        playerService.updateAfterGame(balance, isNextGameFree, player.getId());
    }

}

