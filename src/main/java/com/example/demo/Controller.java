package com.example.demo;

import com.example.demo.dto.InputDto;
import com.example.demo.dto.PlayerDto;
import com.example.demo.dto.SummaryDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class Controller {

    private final GameService gameService;
    private final PlayerService playerService;
    private static final ModelMapper mapper = new ModelMapper();

    public Controller(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @GetMapping("/player/{playerId}")
    public PlayerDto getPlayer(@PathVariable Long playerId) {
        Player player = playerService.getPlayerById(playerId);
        return mapper.map(player, PlayerDto.class);
    }

    @PostMapping("/game")
    public SummaryDto playGame(@Valid @RequestBody InputDto input) {
        Game game = gameService.play(input);
        // todo
        return mapper.map(game, SummaryDto.class);
    }

    @PostMapping("/player")
    public Player createPlayer() {
        return playerService.createPlayer();
    }
}
