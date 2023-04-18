package stopthreadbasedservice.loggingservice;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @author : anger
 */
class LogServiceWithExecutorsTest {

    private final Random random = new Random(100);
    @Test
    void testLog() throws IOException, InterruptedException {
        LogServiceWithExecutors logService =
            new LogServiceWithExecutors(new PrintWriter(
                Files.newOutputStream(Paths.get("./performance/concurrency/cancellationandshutdown/src/test/log.txt"))));
        ConcurrentWorkStream.commonWorkStream(() ->
            logService.log(random.nextDouble() + "")
        ).doWork();
        logService.stop();
        Thread.sleep(100);
    }
}