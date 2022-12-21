package com.example.demo.game;

import com.example.demo.GameProperties;
import com.example.demo.exception.NotValidatedException;
import com.example.demo.player.Player;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

@Component
public class GameEngine {

    private final GameProperties gameProperties;

    private static final Random random = new Random();

    private GameEngine(GameProperties gameProperties) {
//        throw new IllegalStateException();
        this.gameProperties = gameProperties;
    }

    private BigDecimal playGameForCash(BigDecimal bet) {
        Map<String, String> map = gameProperties.getWins();
        int cashRoll = random.nextInt(10);

        return bet.multiply(new BigDecimal(map.getOrDefault(String.valueOf(cashRoll), "0")));
    }

    private boolean playGameForFreeRound() {
        int roundRoll = random.nextInt(10);

        return roundRoll == 1;
    }

    public BigDecimal updateBalance(BigDecimal balance, Game game, GameType type) {
        return switch (type) {
            case REGULAR -> balance.add(game.getPrize()).subtract(game.getBet());
            case FREE -> balance.add(game.getPrize());
        };
    }

    public Game createGame(BigDecimal bet, boolean isFree, Player player) {
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
