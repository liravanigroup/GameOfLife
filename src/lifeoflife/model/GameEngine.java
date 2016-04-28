package lifeoflife.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

/**
 * Sergej Povzaniuk
 * 2016-04-26.
 */
public class GameEngine extends JFrame {

    private JFrame frame = new JFrame();
    private GraphicView view = new GraphicView();
    private Cell[][] generation;
    private int aliveCellPercent;
    private int pixelSize;
    private int stepNumber = 0;
    private int updateSpeed = 1000;


    public GameEngine(int width, int height, double percent, int pixelSize) throws IllegalArgumentException {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width or height can`t be less or equal zero!");
        if (percent < 0 || percent > 1)
            throw new IllegalArgumentException("Percent should be [0,1]!");
        if (pixelSize < 1 || pixelSize > 8)
            throw new IllegalArgumentException("Pixel size should be [1,8]!");

        this.generation = getCellArray(width, height, percent);
        this.aliveCellPercent = (int) (percent * 100);
        initJFrame(width, height, pixelSize);

    }

    private void initJFrame(int width, int height, int pixelSize) {
        this.pixelSize = pixelSize;
        int windowWidth = width * pixelSize;
        int windowHeight = height * pixelSize;
        frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
        frame.setBackground(WHITE);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void start() throws InterruptedException {
        while (getAliveCount() > 0) {
            frame.setTitle("GAME OF LIFE: " + toString());
            frame.setContentPane(view);
            Thread.sleep(updateSpeed);
            getNextGeneration();
            frame.validate();
        }
        System.exit(0);
    }

    public void setUpdateSpeed(int updateSpeed) {
        if (updateSpeed < 0)
            throw new IllegalArgumentException("Speed should be more 0!");
        this.updateSpeed = updateSpeed;
    }

    private Cell[][] getCellArray(int width, int height, double percent) {
        Cell[][] startGenerationStain = getRandomGenerationState(width, height, percent);
        Cell[][] cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell(startGenerationStain[y][x].isAlive(), getNeighborsState(startGenerationStain, x, y));
            }
        }
        return cells;
    }

    private Cell[][] getRandomGenerationState(int width, int height, double percent) {
        Cell[][] result = new Cell[height][width];

        int cellCount = width * height;
        int liveCellCount = (int) (cellCount * percent);
        int startIndex = cellCount - liveCellCount;
        int iterator = 0;

        List arr = new ArrayList(cellCount);
        for (int index = 0; index != cellCount; index++)
            arr.add(index >= startIndex);
        Collections.shuffle(arr);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = new Cell((boolean) arr.get(iterator++));
            }
        }
        return result;
    }

    private void getNextGeneration() {
        stepNumber++;
        Cell[][] nextGeneration = new Cell[generation.length][generation[0].length];
        int y = 0, x = 0;
        for (Cell[] cells : generation) {
            for (Cell cell : cells) {
                cell.updateCellStain();
                nextGeneration[y][x] = new Cell(cell.isAlive(), getNeighborsState(generation, x++, y));
            }
            y++;
            x = 0;
        }
        generation = nextGeneration;
    }

    private boolean[] getNeighborsState(Cell[][] startGenerationStain, int x, int y) {
        boolean[] result = new boolean[8];

        int lenghtY = startGenerationStain.length;
        int lenghtX = startGenerationStain[0].length;

        int yUp = minusOne(lenghtY, y);
        int yDown = plusOne(lenghtY, y);
        int xLeft = minusOne(lenghtX, x);
        int xRight = plusOne(lenghtX, x);

        result[0] = startGenerationStain[yUp][xLeft].isAlive();
        result[1] = startGenerationStain[yUp][x].isAlive();
        result[2] = startGenerationStain[yUp][xRight].isAlive();
        result[3] = startGenerationStain[y][xRight].isAlive();
        result[4] = startGenerationStain[yDown][xRight].isAlive();
        result[5] = startGenerationStain[yDown][x].isAlive();
        result[6] = startGenerationStain[yDown][xLeft].isAlive();
        result[7] = startGenerationStain[y][xLeft].isAlive();

        return result;
    }

    private int plusOne(int lenght, int index) {
        int sum = index + 1;
        if (sum == lenght)
            return 0;
        else
            return sum;
    }

    private int minusOne(int lenght, int index) {
        int different = index - 1;
        if (different == -1)
            return lenght - 1;
        else
            return different;
    }

    private int getAliveCount() {
        int result = 0;
        for (Cell[] cells : generation) {
            for (Cell cell : cells) {
                if (cell.isAlive())
                    result++;
            }
        }
        return result;
    }

    public String toString() {
        return "step: " + stepNumber + " start live: " + aliveCellPercent + "%" + "  live: " + getAliveCount();
    }

    private class GraphicView extends JLabel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int y = 0; y < generation.length; y++) {
                for (int x = 0; x < generation[0].length; x++) {
                    if (generation[y][x].isAlive())
                        g.setColor(BLACK);
                    else
                        g.setColor(WHITE);
                    g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                }
            }
        }
    }
}
