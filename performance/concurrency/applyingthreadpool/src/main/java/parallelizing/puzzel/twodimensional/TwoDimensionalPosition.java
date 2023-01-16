package parallelizing.puzzel.twodimensional;

/**
 * @author : anger
 */
public class TwoDimensionalPosition {
    private final int x;
    private final int y;

    public TwoDimensionalPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TwoDimensionalPosition)
            return this.x == ((TwoDimensionalPosition) other).x &&
                this.y == ((TwoDimensionalPosition) other).y;
        return false;
    }
}
