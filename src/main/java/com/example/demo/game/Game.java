package com.example.demo.game;

import com.example.demo.player.Player;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal bet;

    private BigDecimal prize;

    private Boolean isFreeRoundWon;

    private Boolean isFree; // ?

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

}
