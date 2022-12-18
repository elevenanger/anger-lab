package cn.anger.trackvehicle.publishversion;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;

/**
 * @author : anger
 * 通过使用线程安全的 SafePoint
 * 可以在不破坏线程安全的情况下发布 tracker 的基础可变状态
 */
@ThreadSafe
public class SafePoint {
    @GuardedBy("this") private int x, y;
    // 使用 private 构造函数
    // 如果拷贝构造函数 public SafePoint(SafePoint point)
    // 使用 this(point.x, point.y) 实现
    // 可以防止 race condition
    private SafePoint(int[] a) { this(a[0], a[1]); }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SafePoint(SafePoint point) {
        this(point.get());
    }
    // 通过一个两个元素的数组获取 x 和 y 的值
    // 如果是独立的 getter 方法
    // 可能在获取 x 和 y 之间出现数据不一致的情况
    public synchronized int[] get() {
        return new int[] {x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
