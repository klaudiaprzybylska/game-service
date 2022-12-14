package com.example.demo;

import com.example.demo.dto.InputDto;
import com.example.demo.dto.PlayerDto;
import com.example.demo.dto.SummaryDto;
import com.example.demo.game.Game;
import com.example.demo.game.GameService;
import com.example.demo.player.Player;
import com.example.demo.player.PlayerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class Controller {

    private final GameService gameService;
    private final PlayerService playerService;

    private final ApplicationMapper applicationMapper;

    public Controller(GameService gameService, PlayerService playerService, ApplicationMapper applicationMapper) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.applicationMapper = applicationMapper;
    }

    @GetMapping("/player/{playerId}")
    public PlayerDto getPlayer(@PathVariable Long playerId) {
        Player player = playerService.getPlayerById(playerId);

        return applicationMapper.map(player, PlayerDto.class);
    }

    @PostMapping("/game")
    public SummaryDto playGame(@Valid @RequestBody InputDto input) {
        Game game = gameService.play(input);

        return applicationMapper.map(game, SummaryDto.class);
    }

    @PostMapping("/player")
    public PlayerDto createPlayer() {
        Player player = playerService.createPlayer();

        return applicationMapper.map(player, PlayerDto.class);
    }
}
