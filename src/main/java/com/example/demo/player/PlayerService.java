package com.example.demo.player;

import com.example.demo.exception.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    public void updateAfterGame(BigDecimal newBalance, boolean freeGame, Long id) {
        Player player = getPlayerById(id);
        player.setBalance(newBalance);
        player.setIsNextRoundFree(freeGame);
        playerRepository.save(player);
    }

    public Player createPlayer() {
        return playerRepository.save(Player.builder()
                .playedGames(Collections.emptyList())
                .isNextRoundFree(false)
                .balance(BigDecimal.valueOf(5000))
                .name("aaa")
                .build());
    }
}
