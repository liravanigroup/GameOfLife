package lifeoflife.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Sergej Povzaniuk
 * 2016-04-28.
 */
public class GameEngineTest {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 150;

    private static final int WRONG_HEIGHT = -0;
    private static final int WRONG_WIDTH = 0;

    private static final double PERCENT = 0.25;
    private static final double WRONG_PERCENT = 0;

    private static final int PIXEL_SIZE = 5;
    private static final int WRONG_PIXEL_SIZE = 0;

    private static final int UPDATE_SPEED = 100;
    private static final int WRONG_UPDATE_SPEED = -10;

    @Test
    public void ShouldCreateGame() {
        GameEngine game = new GameEngine(WIDTH, HEIGHT, PERCENT, PIXEL_SIZE);
        Assert.assertNotNull(game);
    }

    @Test
    public void ShouldThrowsIllegalArgumentException() throws IllegalArgumentException {
        try {
            GameEngine game = new GameEngine(WRONG_WIDTH, WRONG_HEIGHT, WRONG_PERCENT, WRONG_PIXEL_SIZE);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //excepted
        }
    }

    @Test
    public void ShouldSetupUpdateSpeed() {
        GameEngine game = new GameEngine(WIDTH, HEIGHT, PERCENT, PIXEL_SIZE);
        game.setUpdateSpeed(UPDATE_SPEED);
        Assert.assertNotNull(game);
    }

    @Test
    public void ShouldSetupWrongUpdateSpeed() {
        GameEngine game = new GameEngine(WIDTH, HEIGHT, PERCENT, PIXEL_SIZE);
        try {
            game.setUpdateSpeed(WRONG_UPDATE_SPEED);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            //excepted
        }
    }

}
