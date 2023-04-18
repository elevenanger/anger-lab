package deadlock.opencalls.deadlockprone;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import deadlock.opencalls.Point;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author : anger
 */
class TaxiTest {
    private final Dispatcher dispatcher = new Dispatcher();
    private final Random randomX = new Random();
    private final Random randomY = new Random();
    @Test
    void testSingleThread() {
        for (int i = 0; i < 500; i++) {
            Taxi taxi = new Taxi(dispatcher);
            taxi.setLocation(new Point(randomX.nextInt(10), randomY.nextInt(10)));
        }

        dispatcher.getImage().print();
    }

    @Test
    void testDeadlock() {
        ConcurrentWorkStream.commonWorkStream(() -> {
            new Taxi(dispatcher).setLocation(
                new Point(randomX.nextInt(10), randomY.nextInt(10)));})
            .merge(ConcurrentWorkStream.commonWorkStream(() -> dispatcher.getImage().print()))
            .doWork();
    }

}