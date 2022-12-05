package com.example.demo;

import java.util.Random;

public class GameEngine {

    private static final Random random = new Random();
    static float playGameForCash(float bet) {
        int cashRoll = random.nextInt(10);

        return switch (cashRoll) {
            case 1 -> bet * 3;
            case 2 -> bet * 10;
            case 3 -> bet * 50;
            default -> 0;
        };
    }

    static boolean playGameForFreeRound() {
        int roundRoll = random.nextInt(10);
        return roundRoll == 1;
    }
}
