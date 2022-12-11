package com.example.demo.game;

import com.example.demo.exception.NotValidatedException;
import com.example.demo.player.Player;

import java.math.BigDecimal;
import java.util.Random;

public class GameEngine {

    private static final Random random = new Random();

    private GameEngine() {
        throw new IllegalStateException();
    }

    static BigDecimal playGameForCash(BigDecimal bet) {
        int cashRoll = random.nextInt(10);

        return switch (cashRoll) {
            case 1 -> bet.multiply(BigDecimal.valueOf(3));
            case 2 -> bet.multiply(BigDecimal.valueOf(10));
            case 3 -> bet.multiply(BigDecimal.valueOf(50));
            default -> BigDecimal.valueOf(0);
        };
    }

    static boolean playGameForFreeRound() {
        int roundRoll = random.nextInt(10);

        return roundRoll == 1;
    }

    static BigDecimal updateBalance(BigDecimal balance, Game game, GameType type) {
        return switch (type) {
            case REGULAR -> balance.add(game.getPrize()).subtract(game.getBet());
            case FREE -> balance.add(game.getPrize());
        };
    }

    static Game createGame(BigDecimal bet, boolean isFree, Player player) {
        if (!isFree && player.getBalance().compareTo(bet) < 0) {
            throw new NotValidatedException("Insufficient funds");
        }

        return Game.builder()
                .bet(bet)
                .prize(playGameForCash(bet))
                .player(player)
                .isFree(isFree)
                .isFreeRoundWon(playGameForFreeRound())
                .build();
    }
}
