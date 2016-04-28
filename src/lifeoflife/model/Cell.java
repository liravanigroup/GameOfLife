package lifeoflife.model;

/**
 * Sergej Povzaniuk
 * 2016-04-26.
 */
class Cell {

    private boolean isAlive;
    private boolean[] neighborsStain = new boolean[8];

    Cell(boolean isAlive, boolean[] neighborsStain) {
        this.isAlive = isAlive;
        this.neighborsStain = neighborsStain;
    }

    Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    boolean isAlive() {
        return isAlive;
    }

    void updateCellStain() {
        int count = getAliveNeighboursCount();
        if (isAlive)
            isAlive = count == 2 || count == 3;
        else
            isAlive = count == 3;
    }

    private int getAliveNeighboursCount() {
        int count = 0;
        for (boolean isAliveCell : neighborsStain) {
            if (isAliveCell)
                count++;
        }
        return count;
    }
}
