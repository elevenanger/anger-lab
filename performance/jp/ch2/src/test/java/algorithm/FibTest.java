package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * author : liuanglin
 * date : 2022/7/27 11:50
 * description : 斐波那契数列测试
 * 微基准测试
 * 针对一小块单元代码进行测试
 * 选择性能最优的实现方案
 * java 代码执行性能的一大特征是一段代码执行的次数越多则这段代码的执行效率越高
 * 对于微基准测试
 * 预热是非常有必要的
 * 不然就是测试编译的性能而非代码实际执行的性能
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