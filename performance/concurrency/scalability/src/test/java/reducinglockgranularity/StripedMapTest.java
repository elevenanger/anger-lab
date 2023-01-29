package reducinglockgranularity;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

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