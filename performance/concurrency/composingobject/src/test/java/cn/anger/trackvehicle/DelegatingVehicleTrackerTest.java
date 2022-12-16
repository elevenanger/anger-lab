package cn.anger.trackvehicle;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class DelegatingVehicleTrackerTest {
    private final DelegatingVehicleTracker tracker =
        new DelegatingVehicleTracker(initMap());

    private final AtomicInteger idTracker = new AtomicInteger(0);

    private HashMap<String, Point> initMap() {
        HashMap<String, Point> map = new HashMap<>();
        for (int i = 0; i < 1000; i++)
            map.put(String.valueOf(i), new Point(0, 0));
        return map;
    }

    List<Thread> pool = Stream.generate(() ->
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tracker.setLocation(
                    String.valueOf(idTracker.getAndIncrement()),
                    1, 1
                );
            }
        }))
        .limit(10)
        .collect(Collectors.toList());


    @Test
    void delegatingVehicleTrackerTest() {
        pool.forEach(Thread::start);
        pool.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Point point = new Point(1, 1);

        for (int i = 0; i < 1000; i++)
            assertEquals(
                tracker.getLocation(String.valueOf(i)),
                point
            );

    }
}