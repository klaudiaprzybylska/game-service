package com.example.demo;

import com.example.demo.dto.InputDto;
import com.example.demo.dto.PlayerDto;
import com.example.demo.dto.SummaryDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {

    private final GameService gameService;
    private final PlayerService playerService;

    public Controller(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @GetMapping("/balance/{playerId}")
    public PlayerDto showBalance(@PathVariable Long playerId) {
        return playerService.showBalance(playerId);
    }

    @PostMapping("/play")
    public SummaryDto playGame(@RequestBody InputDto input) {
        return gameService.playGame(input);
    }

    @GetMapping("/newPlayer")
    public Player createPlayer() {
        return playerService.createPlayer();
    }
}
