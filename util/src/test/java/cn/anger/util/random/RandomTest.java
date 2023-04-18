package cn.anger.util.random;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author : anger
 */
class RandomTest {

    public static final int BASE = 100;
    public static final int DRIFT = 20;

    @Test
    void getRandom() {
        Stream.generate(() -> Random.getRandom(BASE, DRIFT))
            .limit(1000)
            .forEach(System.out::println);
    }
}