package narrowlock;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @author : anger
 */
class AttributeStoreTest {
    private final AttributeStore store = new AttributeStore();
    private final BetterAttributeStore betterAttributeStore = new BetterAttributeStore();
    private final Random random = new Random();

    @BeforeEach
    void setUp() {
        Stream.generate(() -> String.format("anger%d", random.nextInt()))
            .limit(1_000)
            .forEach(name -> {
                store.setUserLocationAttributes(name, String.format("W Y %d", random.nextInt()));
                betterAttributeStore.setUserLocationAttributes(name, String.format("W Y %d", random.nextInt()));
            });
    }

    @Test
    void userLocationMatchTest() {
        ConcurrentWorkStream.heavyWorkStream(() -> {
            String name = String.format("anger%d", random.nextInt());
            store.userLocationMatches(name, "W");
        }).doWork();
        ConcurrentWorkStream.heavyWorkStream(() -> {
            String name = String.format("anger%d", random.nextInt());
            betterAttributeStore.userLocationMatches(name, "W");
        }).doWork();
    }

}