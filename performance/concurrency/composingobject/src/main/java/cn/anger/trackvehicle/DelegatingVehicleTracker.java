package cn.anger.trackvehicle;

import cn.anger.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author : anger
 * DelegatingVehicleTracker 并没有使用任何显式的同步机制
 * 但是对于其状态的访问都通过 ConcurrentHashMap 进行管理
 * Map 中所有的 key 和 value 值都是不可变的
 *
 */
@ThreadSafe
public class DelegatingVehicleTracker {
    // 委派 ConcurrentMap 实现线程安全
    // 不能将该对象发布出去
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    // 返回一个浅拷贝的静态版本
    public Map<String, Point> getCurrentLocations() {
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("Invalid vehicle name: " + id);
    }
}