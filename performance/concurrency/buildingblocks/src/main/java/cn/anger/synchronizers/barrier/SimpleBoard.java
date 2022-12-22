package cn.anger.synchronizers.barrier;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : anger
 */
public class SimpleBoard implements Board {
    private int result;
    private final int index;
    private final int numPartitions;
    private final int maxX;
    private final int maxY;
    boolean hasConverged = false;
    private final List<SimpleBoard> subBoards = new CopyOnWriteArrayList<>();
    public SimpleBoard(int maxX, int maxY, int index, int numPartitions) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.index = index;
        this.numPartitions = numPartitions;
    }

    @Override
    public int getMaxX() {
        return maxX;
    }

    @Override
    public int getMaxY() {
        return maxY;
    }

    @Override
    public int getValue() {
        return result;
    }

    @Override
    public int setNewValue(int x, int y, int value) {
        if (x == maxX && y == maxY) {
            result *= index;
            System.out.printf("Board %d calculation finished, has converged.\n", index);
            hasConverged = true;
        } else
            result += (x + y + value);
        return result;
    }

    @Override
    public void commitNewValue() {
        if (!subBoards.isEmpty()) {
            System.out.printf("\nAll Sub Board converged, " +
                                "%s trigger to commit value:\n", Thread.currentThread().getName());
            subBoards.forEach(Board::commitNewValue);
        }
        else {
            System.out.printf(
                "\nPartitions     => %d\n" +
                    "index          => %d\n" +
                    "commit value   => %d\n\n",
                numPartitions, index, result);
        }
    }

    @Override
    public boolean hasConverged() {
        return hasConverged;
    }

    @Override
    public void waitForConvergence() {
        System.out.printf("Board %d waiting for convergence.\n", index);
    }

    @Override
    public Board getSubBoard(int numPartitions, int index) {
        SimpleBoard subBoard =
            new SimpleBoard(maxX, maxY, index, numPartitions);
        subBoards.add(subBoard);
        subBoard.waitForConvergence();
        return subBoard;
    }
}
