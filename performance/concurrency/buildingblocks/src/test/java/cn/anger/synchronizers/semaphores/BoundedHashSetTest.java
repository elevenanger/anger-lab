package cn.anger.synchronizers.semaphores;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class BoundedHashSetTest {

    private final BoundedHashSet<Integer> set = new BoundedHashSet<>(100);

    @Test
    void testAdd() {
        IntStream.range(0, 100)
            .boxed()
            .forEach(i -> {
                try {
                    if (!set.add(i))
                        System.out.println("set is full ");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}