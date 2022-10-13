package com.example.demo;

import com.example.demo.dto.InputDto;
import com.example.demo.dto.SummaryDto;
import com.example.demo.exception.NotValidatedException;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerService playerService;

    public GameService(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }

    private static final Random random = new Random();

    private Game createGame(InputDto input, Player player, boolean isFree) {
        Game game = Game.builder()
                .bet(input.getBet())
                .prize(playGameForCash(input.getBet()))
                .player(player)
                .isFree(isFree)
                .isFreeRoundWon(playGameForFreeRound())
                .build();
        return gameRepository.save(game);
    }

    private int playGameForCash(int bet) {
        int cashRoll = random.nextInt(10);

        return switch (cashRoll) {
            case 1 -> bet * 3;
            case 2 -> bet * 10;
            case 3 -> bet * 50;
            default -> 0;
        };
    }

    private boolean playGameForFreeRound() {
        int roundRoll = random.nextInt(10);
        return roundRoll == 1;
    }

    public SummaryDto playGame(InputDto input) {
        if (input.getBet() > 10 || input.getBet() < 0)
            throw new NotValidatedException("Please provide a bet between 1 and 10");

        Player player = playerService.getPlayerById(input.getPlayerId());
        int newBalance = player.getBalance();
        Game game;
        if (input.isFree() || player.getIsNextRoundFree()) {
            game = createGame(input, player, true);
            newBalance += game.getPrize();
        } else {
            game = createGame(input, player, false);
            newBalance = newBalance - game.getBet() + game.getPrize();
        }
        playerService.updateAfterGame(newBalance, game.getIsFreeRoundWon(), player.getId());
        return SummaryDto.builder()
                .prize(game.getPrize())
                .isFreeRoundWon(game.getIsFreeRoundWon())
                .newBalance(newBalance)
                .build();
    }
}
