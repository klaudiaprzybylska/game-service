package com.example.demo;

import com.example.demo.dto.PlayerDto;
import com.example.demo.exception.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Player with id " + id + " not found"));
    }

    public void updateAfterGame(int newBalance, boolean freeGame, Long id) {
        Player player = getPlayerById(id);
        player.setBalance(newBalance);
        player.setIsNextRoundFree(freeGame);
        playerRepository.save(player);
    }

    public PlayerDto showBalance(Long playerId) {
        Player player = getPlayerById(playerId);
        return PlayerDto.builder()
                .balance(player.getBalance())
                .playerId(playerId)
                .name(player.getName())
                .isNextFree(player.getIsNextRoundFree())
                .build();
    }

    public Player createPlayer() {
        return playerRepository.save(Player.builder()
                .playedGames(Collections.emptyList())
                .isNextRoundFree(false)
                .balance(5000)
                .name("aaa")
                .build());
    }
}
