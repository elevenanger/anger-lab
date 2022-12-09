package cn.anger.servlet;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author : anger
 */
public class StatelessFactorizerTest {

    @Test
    public void doStatelessFactorizer() {
        handleStatelessFactorizer.accept(100);
    }

    @Test
    void testConcurrentFactorizer() {
        IntStream.range(1_000, 1_010).parallel()
            .boxed()
            .forEach(handleStatelessFactorizer);
    }

    @Test
    void testMultiThreadFactorizer() {
        final List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threads.add(new Thread(
                () -> handleStatelessFactorizer.accept(finalI * 1_000)
            ));
        }

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // StatelessFactorizer 是线程安全的，只需要创建一个实例即可
    final StatelessFactorizer servlet = new StatelessFactorizer();
    final Consumer<Integer> handleStatelessFactorizer = number -> {
        System.out.println(Thread.currentThread().getName());
        ServletRequest request = new MockHttpServletRequest();
        request.setAttribute("number", new BigInteger(String.valueOf(number)));
        ServletResponse response = new MockHttpServletResponse();

        servlet.service(request, response);
    };


}