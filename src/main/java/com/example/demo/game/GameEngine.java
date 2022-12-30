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

    private final Random random;

    public GameEngine(GameProperties gameProperties, Random random) {
//        throw new IllegalStateException();
        this.gameProperties = gameProperties;
        this.random = random;
    }

    private BigDecimal playGameForCash(BigDecimal bet) {
        Map<Integer, BigDecimal> map = gameProperties.getWins();
        int cashRoll = random.nextInt(10);

        BigDecimal multiplicand = map.getOrDefault(cashRoll, BigDecimal.ZERO);
        return bet.multiply(multiplicand);
    }

    private boolean playGameForFreeRound() {
        int roundRoll = random.nextInt(10);

        return roundRoll == 1;
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
