package cn.anger.servlet;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
public class FactorizerTest<T extends Servlet> {
    final static Integer THREAD_NUM = 10;
    final static Integer LOOP_COUNT = 1_000;
    private final static BigInteger TO_BE_FAC = new BigInteger("1000");

    final Consumer<T> toFactorizer = servlet -> {
        ServletRequest request = new MockHttpServletRequest();
        request.setAttribute("number", TO_BE_FAC);
        ServletResponse response = new MockHttpServletResponse();

        try {
            servlet.service(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    };

    final Function<T, Runnable> genTask = servlet -> () -> {
        for (int i = 0; i < LOOP_COUNT; i++) {
            toFactorizer.accept(servlet);
        }
    };

    final Function<Runnable, List<Thread>> toWorkers = runnable ->
        Stream.generate(() -> new Thread(runnable))
            .limit(THREAD_NUM)
            .collect(Collectors.toList());

    final void factorizationChain(T servlet) {
        List<Thread> threads = genTask.andThen(toWorkers).apply(servlet);
        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
