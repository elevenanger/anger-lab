package cn.anger.servlet;

import org.junit.jupiter.api.TestTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 * Factorizer 测试父类
 * 实现测试相关的公共方法
 */
abstract class FactorizerTest<T extends Factorizer> {
    final Random random = new Random();
    final static Integer THREAD_NUM = 10;
    final static Integer LOOP_COUNT = 10;

    // 多个线程之间共享唯一实例
    final T servlet = initializeFactorizer();

    abstract T initializeFactorizer();

    void requestToFactorizer() {
        ServletRequest request = new MockHttpServletRequest();
        request.setAttribute("number",
            BigInteger.valueOf(random.nextInt(100)));
        ServletResponse response = new MockHttpServletResponse();
        servlet.service(request, response);
    }

    final Runnable requestLoop = () -> {
        for (int i = 0; i < LOOP_COUNT; i++) {
            requestToFactorizer();
        }
    };

    final Function<Runnable, List<Thread>> toWorkers = runnable ->
        Stream.generate(() -> new Thread(runnable))
            .limit(THREAD_NUM)
            .collect(Collectors.toList());

    final void genericMultiThreadFactorizationChain() {
        List<Thread> threads = toWorkers.apply(requestLoop);
        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @TestTemplate
    abstract void testMultiThreadFactorizer();

}
