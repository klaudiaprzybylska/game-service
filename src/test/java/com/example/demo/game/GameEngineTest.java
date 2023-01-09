package com.example.demo.game;

import com.example.demo.GameProperties;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameEngineTest {

    private Map<Integer, BigDecimal> wins = new HashMap<>();

    private static Stream<Arguments> playForRound() {
        return Stream.of(Arguments.of(0, false),
                Arguments.of(1, true),
                Arguments.of(2, false),
                Arguments.of(3, false),
                Arguments.of(4, false),
                Arguments.of(5, false),
                Arguments.of(6, false),
                Arguments.of(7, false),
                Arguments.of(8, false),
                Arguments.of(9, false)
        );
    }

    private static Stream<Arguments> playForCash() {
        return Stream.of(Arguments.of(0, new BigDecimal(0)),
                Arguments.of(1, new BigDecimal(3)),
                Arguments.of(2, new BigDecimal(10)),
                Arguments.of(3, new BigDecimal(50)),
                Arguments.of(4, new BigDecimal(0)),
                Arguments.of(5, new BigDecimal(0)),
                Arguments.of(6, new BigDecimal(0)),
                Arguments.of(7, new BigDecimal(0)),
                Arguments.of(8, new BigDecimal(0)),
                Arguments.of(9, new BigDecimal(0))
        );
    }

    @ParameterizedTest
    @MethodSource("playForRound")
    public void playGameForFreeRoundTest(int roll, boolean free) {
        // given
        Random random = mock(Random.class, withSettings().withoutAnnotations());
        GameProperties gameProperties = new GameProperties();
        GameEngine gameEngine = new GameEngine(gameProperties, random);
        when(random.nextInt(10)).thenReturn(roll);

        // when
        Boolean balance = gameEngine.playGameForFreeRound();

        // then
        assertEquals(free, balance);
    }
    @ParameterizedTest
    @MethodSource("playForCash")
    public void playGameForCashTest(int roll, BigDecimal multiplicand) {
        // given
        wins.put(1, new BigDecimal(3));
        wins.put(2, new BigDecimal(10));
        wins.put(3, new BigDecimal(50));
        GameProperties gameProperties = new GameProperties(new BigDecimal(1), new BigDecimal(10), wins);
        Random random = mock(Random.class, withSettings().withoutAnnotations());
        GameEngine gameEngine = new GameEngine(gameProperties, random);
        when(random.nextInt(10)).thenReturn(roll);
        BigDecimal bet = new BigDecimal(1);
        BigDecimal expected = bet.multiply(multiplicand);

        // when
        BigDecimal balance = gameEngine.playGameForCash(bet);

        // then
        assertEquals(expected, balance);
    }
}