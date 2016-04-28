package lifeoflife.application;
/**
 * Sergej Povzaniuk
 * 2016-04-27.
 */
import lifeoflife.model.GameEngine;
import java.io.IOException;

public class GameOfLifeInWindow  {
    public static void main(String[] args) throws InterruptedException, IOException {
        GameEngine game = new GameEngine(100, 100, 0.10, 5);
        game.setUpdateSpeed(100);
        game.start();
    }
}






