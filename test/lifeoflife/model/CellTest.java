package lifeoflife.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Sergej Povzaniuk
 * 2016-04-26.
 */
public class CellTest {

    private static final boolean[] MUST_DIE_0 = {false, false, false, false, false, false, false, false};
    private static final boolean[] MUST_DIE_1 = {true, false, false, false, false, false, false, false};
    private static final boolean[] MUST_DIE_4 = {true, true, true, true, false, false, false, false};
    private static final boolean[] MUST_DIE_5 = {true, true, true, true, true, false, false, false};
    private static final boolean[] MUST_DIE_6 = {true, true, true, true, true, true, false, false};
    private static final boolean[] MUST_DIE_7 = {true, true, true, true, true, true, true, false};
    private static final boolean[] MUST_DIE_8 = {true, true, true, true, true, true, true, true};

    private static final boolean[] CONTINUE_LIVE_IF_2 = {true, true, false, false, false, false, false, false};
    private static final boolean[] CONTINUE_LIVE_IF_3 = {true, true, true, false, false, false, false, false};

    private static final boolean[] NEW_BORN = {true, true, true, false, false, false, false, false};

    private static final boolean IS_ALIVE = true;
    private static final boolean IS_DEAD = false;


    @Test
    public void shouldCreateCell() {
        Cell cell = new Cell(IS_ALIVE, CONTINUE_LIVE_IF_3);
        cell.updateCellStain();
        Assert.assertNotNull(cell);
    }

    @Test
    public void shouldKillCell0() {
        Cell cell = new Cell(IS_ALIVE, MUST_DIE_0);
        cell.updateCellStain();
        Assert.assertFalse(cell.isAlive());
    }

    @Test
    public void shouldKillCell1() {
        Cell cell = new Cell(IS_ALIVE, MUST_DIE_1);
        cell.updateCellStain();
        Assert.assertFalse(cell.isAlive());
    }

    @Test
    public void shouldKillCell4() {
        Cell cell = new Cell(IS_ALIVE, MUST_DIE_4);
        cell.updateCellStain();
        Assert.assertFalse(cell.isAlive());
    }

    @Test
    public void shouldKillCell5() {
        Cell cell = new Cell(IS_ALIVE, MUST_DIE_5);
        cell.updateCellStain();
        Assert.assertFalse(cell.isAlive());
    }

    @Test
    public void shouldKillCell6() {
        Cell cell = new Cell(IS_ALIVE, MUST_DIE_6);
        cell.updateCellStain();
        Assert.assertFalse(cell.isAlive());
    }

    @Test
    public void shouldKillCell7() {
        Cell cell = new Cell(IS_ALIVE, MUST_DIE_7);
        cell.updateCellStain();
        Assert.assertFalse(cell.isAlive());
    }

    @Test
    public void shouldKillCell8() {
        Cell cell = new Cell(IS_ALIVE, MUST_DIE_8);
        cell.updateCellStain();
        Assert.assertFalse(cell.isAlive());
    }

    @Test
    public void shouldRetCell() {
        Cell cell = new Cell(IS_DEAD, NEW_BORN);
        cell.updateCellStain();
        Assert.assertTrue(cell.isAlive());
    }

    @Test
    public void shouldContinueCell2() {
        Cell cell = new Cell(IS_ALIVE, CONTINUE_LIVE_IF_2);
        Assert.assertTrue(cell.isAlive());
    }

    @Test
    public void shouldContinueCell3() {
        Cell cell = new Cell(IS_ALIVE, CONTINUE_LIVE_IF_3);
        Assert.assertTrue(cell.isAlive());
    }
}
