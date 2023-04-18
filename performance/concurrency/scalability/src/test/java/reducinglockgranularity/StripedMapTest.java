package reducinglockgranularity;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author : anger
 */
class StripedMapTest {

    final Random random = new Random();
    final Supplier<String> randomString = () ->
        String.format("anger%d", random.nextInt(1000));

    @Test
    void stripedMapTest() {
        StripedMap<String, Integer> stripedMap = new StripedMap<>(1000);
        ConcurrentWorkStream.commonWorkStream(() ->
            stripedMap.put(randomString.get(), random.nextInt(1000)))
            .doWork();
        System.out.println(stripedMap);
    }

}