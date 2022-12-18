package cn.anger.trackvehicle.publishversion;

import cn.anger.annotation.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : anger
 * getLocations() 会返回一个不可变的 map
 * 调用者无法新增或者删除其中的元素
 * 但是由于 map 的机制
 * 可以修改获取到的某一个元素
 * 这种机制对于业务场景是一个好处还是缺陷，
 * 取决于如何使用它
 * PublishingVehicleTracker 是线程安全的
 * 但是如果需要对于 SafePoint 的数据进行额外的校验
 * 或者当 SafePoint 发生变化需要进行联动的操作，
 * 则不适合使用这种方式，
 * 因为调用者可以改变单一元素的状态
 */
@ThreadSafe
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return locations;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id))
            System.out.println("invalid vehicle name : " + id);
        locations.get(id).set(x, y);
    }
}
