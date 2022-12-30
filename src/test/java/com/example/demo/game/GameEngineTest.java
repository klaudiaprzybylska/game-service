package com.example.demo.game;

import com.example.demo.GameProperties;
import com.example.demo.exception.NotValidatedException;
import com.example.demo.player.Player;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private final GameProperties gameProperties = new GameProperties();

    private final GameEngine gameEngine = new GameEngine(gameProperties);

    @Test
    public void updateBalanceForFreeGame() {
        // given
        BigDecimal expectedValue = new BigDecimal(101);
        Game game = Game.builder()
                .bet(new BigDecimal(1))
                .prize(new BigDecimal(1))
                .build();

        // when
        BigDecimal balance = gameEngine.updateBalance(new BigDecimal(100), game, GameType.FREE);

        // then
        assertEquals(expectedValue, balance);
    }

    @Test
    public void updateBalanceForRegularGame() {
        // given
        BigDecimal expectedValue = new BigDecimal(100);
        Game game = Game.builder()
                .bet(new BigDecimal(1))
                .prize(new BigDecimal(1))
                .build();

        // when
        BigDecimal balance = gameEngine.updateBalance(new BigDecimal(100), game, GameType.REGULAR);

        // then
        assertEquals(expectedValue, balance);
    }

    @Test
    public void createGameWithInsufficientFunds() {
        // given
        Player player = Player.builder()
                .balance(new BigDecimal(0))
                .build();

        // when


        // then
        assertThrows(NotValidatedException.class, () -> gameEngine.createGame(new BigDecimal(1), false, player));
    }

    @Test
    public void createGameWithSufficientFunds() {
        // given
        Player player = Player.builder()
                .balance(new BigDecimal(10))
                .build();

        // when
        Game actualGame = gameEngine.createGame(new BigDecimal(1), false, player);

        // then
        assertNotNull(actualGame);
    }
}