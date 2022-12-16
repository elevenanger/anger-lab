package cn.anger.trackvehicle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 * 测试 MonitorVehicleTracker 线程安全
 */
class MonitorVehicleTrackerTest {
    private final AtomicInteger idTracker = new AtomicInteger(0);
    private final MonitorVehicleTracker tracker =
        new MonitorVehicleTracker(initLocations());

    // 使用多线程为 tracker 中的每个 point 设置位置
    private final List<Thread> pool = Stream.generate(() -> new Thread(
        () -> {
            for (int i = 0; i < 100; i++) {
                tracker.setLocation(
                    String.valueOf(idTracker.getAndIncrement()),
                    1, 1
                );}}))
        .limit(10)
        .collect(Collectors.toList());

    // 初始化所有的位置
    private Map<String, MutablePoint> initLocations() {
        final Map<String, MutablePoint> map = new HashMap<>();
        for (int i = 0; i < 1000; i++)
            map.put(String.valueOf(i), new MutablePoint());
        return map;
    }

    @Test
    void testConcurrentSetLocation() {
        pool.forEach(Thread::start);
        pool.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        MutablePoint point = new MutablePoint();
        point.x = 1;
        point.y = 1;
        // 比较所有的位置都是新设置的位置
        // 因为 MonitorVehicleTracker 是线程安全的
        // 所以所有的位置都应该在多线程环境下正确设置完成
        for (int i = 0; i < 1000; i++) {
            Assertions.assertEquals(
                tracker.getLocation(String.valueOf(i)),
                point
            );
        }
    }
}