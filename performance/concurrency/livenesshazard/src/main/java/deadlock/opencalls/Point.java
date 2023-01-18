package deadlock.opencalls;

/**
 * @author : anger
 * 坐标
 */
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point)
            return this.x == ((Point) obj).x &&
                this.y == ((Point) obj).y;
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
