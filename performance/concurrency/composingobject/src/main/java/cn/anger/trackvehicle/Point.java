package cn.anger.trackvehicle;

import cn.anger.annotation.Immutable;

/**
 * @author : anger
 * Point 是线程安全的类因为其是不可变的
 */
@Immutable
public class Point {
    public final int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point))
            return false;
        return this.x == ((Point) obj).x &&
                this.y == ((Point) obj).y;
    }
}
