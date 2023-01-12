package saturation;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class BoundedExecutorTest {

    @Test
    void testBoundedExecutor() {
        BoundedExecutor executor = new BoundedExecutor(
            Executors.newSingleThreadExecutor(),
            10
        );

        IntStream.rangeClosed(0, 100)
            .parallel()
            .forEach(i -> {
                try {
                    executor.submitTask(() -> System.out.printf("submit task %d%n", i));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }

}