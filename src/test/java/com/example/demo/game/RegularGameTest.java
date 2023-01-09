package com.example.demo.game;

import com.example.demo.dto.InputDto;
import com.example.demo.exception.NotValidatedException;
import com.example.demo.player.Player;
import com.example.demo.player.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RegularGameTest {

    @Mock
    private PlayerService playerService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameEngine gameEngine;

    private RegularGame regularGame;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        regularGame = new RegularGame(gameRepository, playerService, gameEngine);
    }

    @Test
    void isTypeFree() {
        // given
        GameType expected = GameType.REGULAR;

        // when
        GameType actual = regularGame.getType();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void createGame() {
        // given
        InputDto inputDto = InputDto.builder()
                .bet(new BigDecimal(10))
                .build();
        Player player = Player.builder()
                .balance(new BigDecimal(20))
                .build();
        Game expected = Game.builder()
                .isFree(true)
                .build();
        when(gameEngine.createGame(inputDto.getBet(), false, player)).thenReturn(expected);
        when(gameRepository.save(expected)).thenReturn(expected);

        // when
        Game actual = regularGame.createGame(inputDto, player);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void createGameWithInsufficientFunds() {
        // given
        InputDto inputDto = InputDto.builder()
                .bet(new BigDecimal(10))
                .build();
        Player player = Player.builder()
                .balance(new BigDecimal(0))
                .build();
        Game expected = Game.builder()
                .isFree(true)
                .build();
        when(gameEngine.createGame(inputDto.getBet(), false, player)).thenReturn(expected);
        when(gameRepository.save(expected)).thenReturn(expected);

        // when

        // then
        assertThrows(NotValidatedException.class, () -> regularGame.createGame(inputDto, player));
    }
}