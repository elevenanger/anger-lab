package cn.anger.random;

/**
 * @author : anger
 */
public class Random {
    private Random() {}

    public static int getRandom() {
        int t = (int) System.nanoTime();
        return xorShift(t);
    }

    public static int getRandom(int num) {
        int seed = num ^ (int) System.nanoTime();
        return xorShift(seed);
    }

    public static int getRandomWithLimit(int limit) {
        return getRandom() % limit;
    }

    private static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return Math.abs(y);
    }
}
