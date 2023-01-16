package parallelizing;

import cn.anger.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;
import threadcreationandshutdown.CustomizeThreadPoolExecutor;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
class ElementPrinterTest {

    private final Random random = new Random();
    private final List<Integer> integers =
        Stream.generate(() -> random.nextInt(1_000_000))
            .limit(100)
            .collect(Collectors.toList());
    private final ElementPrinter<Integer> printer = new ElementPrinter<>();

    @Test
    void elementPrinterSequentiallyTest() {
        printer.processSequentially(integers);
    }

    @Test
    void elementPrinterParallelTest() {
        ThreadPoolExecutor exec = CustomizeThreadPoolExecutor.newTimingThreadPool();
        printer.processParallel(exec, integers);
    }
}