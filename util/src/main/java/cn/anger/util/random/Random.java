package cn.anger.util.random;

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

    /**
     * 获取在一定范围内浮动的随机数
     * @param base 基准数值
     * @param drift 浮动范围
     * @return 最终数值
     */
    public static int getRandom(int base, int drift) {
        return base + getRandomWithLimit(drift);
    }

    private static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return Math.abs(y);
    }
}
