package com.example.demo.player;

import com.example.demo.game.Game;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal balance;

    private Boolean isNextRoundFree;

    @OneToMany(mappedBy = "player")
    private List<Game> playedGames;
}
