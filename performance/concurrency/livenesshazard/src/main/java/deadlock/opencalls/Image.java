package deadlock.opencalls;

/**
 * @author : anger
 */
public class Image {
    private final int[][] map = new int[10][10];

    public void drawMarker(Point location) {
        map[location.getX()][location.getY()] += 1;
    }

    public void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%4d", map[j][i]);
            }
            System.out.println();
        }
    }

}
