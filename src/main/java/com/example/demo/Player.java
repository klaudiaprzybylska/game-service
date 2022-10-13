package com.example.demo;

import lombok.*;

import javax.persistence.*;
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
    private int balance;
    private Boolean isNextRoundFree;

    @OneToMany(mappedBy = "player")
    private List<Game> playedGames;
}
