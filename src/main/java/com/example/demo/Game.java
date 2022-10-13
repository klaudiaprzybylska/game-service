package com.example.demo;

import lombok.*;

import javax.persistence.*;

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
    private int bet;
    private int prize;
    private Boolean isFreeRoundWon;
    private Boolean isFree; // ?
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

}
