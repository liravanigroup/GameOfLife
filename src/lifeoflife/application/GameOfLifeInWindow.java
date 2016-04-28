package lifeoflife.application;
/**
 * Sergej Povzaniuk
 * 2016-04-27.
 */

import lifeoflife.model.GameEngine;

public class GameOfLifeInWindow {
    public static void main(String[] args) {
        try {
            GameEngine game = new GameEngine(300, 150, 0.25, 5);
            game.setUpdateSpeed(100);
            game.start();
        } catch (InterruptedException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}






