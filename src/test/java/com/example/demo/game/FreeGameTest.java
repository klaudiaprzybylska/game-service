package com.example.demo.game;

import com.example.demo.dto.InputDto;
import com.example.demo.player.Player;
import com.example.demo.player.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FreeGameTest {

    @Mock
    private PlayerService playerService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameEngine gameEngine;

    private FreeGame freeGame;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        freeGame = new FreeGame(gameRepository, playerService, gameEngine);
    }

    @Test
    void isTypeFree() {
        // given
        GameType expected = GameType.FREE;

        // when
        GameType actual = freeGame.getType();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void createGame() {
        // given
        InputDto inputDto = InputDto.builder()
                .bet(new BigDecimal(10))
                .build();
        Player player = new Player();
        Game expected = Game.builder()
                .isFree(true)
                .build();
        when(gameEngine.createGame(inputDto.getBet(), true, player)).thenReturn(expected);
        when(gameRepository.save(expected)).thenReturn(expected);

        // when
        Game actual = freeGame.createGame(inputDto, player);

        // then
        assertEquals(expected, actual);
    }
}