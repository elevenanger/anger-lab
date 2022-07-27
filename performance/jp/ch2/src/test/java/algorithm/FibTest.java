package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * author : liuanglin
 * date : 2022/7/27 11:50
 * description : 斐波那契数列测试
 */
@Slf4j
class FibTest {

    long startTime;

    @BeforeEach
    void start() {
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    void end() {
        log.info("交易耗时：{}", System.currentTimeMillis() - startTime);
    }

    @Test
    void testNormalWay() {
        for (int i = 0; i < 50; i++) {
            Fib.normalWay(40);
        }
        log.info("预热耗时：{}", System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            Fib.normalWay(40);
        }
    }

    @Test
    void testArrayWay() {
        for (int i = 0; i < 50; i++) {
            Fib.arrayWay(50);
        }
        log.info("预热耗时：{}", System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            Fib.arrayWay(50);
        }
    }

    @Test
    void testStream() {
        Fib fib = new Fib();
        for (int i = 0; i < 50; i++) {
            fib.numbers(50);
        }
        log.info("预热耗时：{}", System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            fib.numbers(50);
        }
    }

}