package cn.anger.trackvehicle;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : anger
 * Tracker 是线程安全的类
 * 即使 Map 和 MutablePoint 是非线程安全的
 * 但是它们不会被发布出去
 * 当需要返回 location 给调用者
 * 对象的值将会使用 MutablePoint Copy 构造器
 * 或者 deepCopy() 方法复制一份给调用者
 * 这种方式通过在返回给调用者之前拷贝可变状态来确保线程安全
 */
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null)
            throw new IllegalArgumentException("No such Id : " + id);
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(
        Map<String, MutablePoint> map) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : map.keySet()) {
            result.put(id, new MutablePoint(map.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}