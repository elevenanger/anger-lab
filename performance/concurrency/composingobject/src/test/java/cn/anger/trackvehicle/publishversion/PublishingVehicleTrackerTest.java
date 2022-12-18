package cn.anger.trackvehicle.publishversion;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : anger
 */
class PublishingVehicleTrackerTest {
    Map<String, SafePoint> safePointMap = new HashMap<>();
    private PublishingVehicleTracker tracker;
    final AtomicInteger idTracker = new AtomicInteger(0);

    @BeforeEach
    void setUp() {
        idTracker.set(0);
        safePointMap = new HashMap<>();
        // 为 map 新增元素，方便测试这里新增的都是 0
        final Consumer<Integer> zeroedSafePoint = i ->
            safePointMap.put(String.valueOf(i), new SafePoint(0, 0));
        IntStream.rangeClosed(0, 1000).boxed().forEach(zeroedSafePoint);
        tracker = new PublishingVehicleTracker(safePointMap);
    }

    @Test
    void getLocation() {
        Runnable checkGetLocation = () -> {
            Integer idNum = idTracker.getAndIncrement();
            SafePoint curLocation = tracker.getLocation(String.valueOf(idNum));
            assertEquals(curLocation.get()[0], 0);
            assertEquals(curLocation.get()[1], 0);
        };
        ConcurrentWorkStream.commonWorkStream(checkGetLocation).doWork();
    }

    @Test
    void setLocation() {
        Runnable setDiploidLocation = () -> {
            int curNum = idTracker.getAndIncrement();
            tracker.setLocation(String.valueOf(curNum), curNum, curNum * 2);
        };
        ConcurrentWorkStream.commonWorkStream(setDiploidLocation).doWork();

        IntStream.rangeClosed(0, 1000)
            .forEach(i -> {
                SafePoint point = tracker.getLocation(String.valueOf(i));
                assertEquals(point.get()[0] * 2, point.get()[1]);
            });
    }
}