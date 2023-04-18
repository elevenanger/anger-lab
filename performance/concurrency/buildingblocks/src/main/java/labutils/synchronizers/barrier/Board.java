package labutils.synchronizers.barrier;

/**
 * @author : anger
 */
public interface Board {
    int getMaxX();
    int getMaxY();
    int getValue();
    int setNewValue(int x, int y, int value);
    void commitNewValue();
    boolean hasConverged();
    void waitForConvergence();
    Board getSubBoard(int numPartitions, int index);
}
