package cn.anger.trackvehicle;

import cn.anger.annotation.NotThreadSafe;

/**
 * @author : anger
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;
    public MutablePoint() {
        x = 0;
        y = 0;
    }
    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MutablePoint))
            return false;
        return this.x == ((MutablePoint) obj).x &&
                this.y == ((MutablePoint) obj).y;
    }
}
