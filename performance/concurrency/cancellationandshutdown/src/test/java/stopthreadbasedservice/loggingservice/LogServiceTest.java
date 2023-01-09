package stopthreadbasedservice.loggingservice;

import cn.anger.concurrency.ConcurrentWorkStream;
import cn.anger.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @author : anger
 */
class LogServiceTest {

    private final Random random = new Random(1000);

    @Test
    void testLog() throws IOException {
        LogService logService = new LogService(new PrintWriter(
            Files.newOutputStream(Paths.get("./performance/concurrency/cancellationandshutdown/src/test/log.txt"))));
        logService.start();
        ConcurrentWorkStream.commonWorkStream(() -> {
            try {
                logService.log(random.nextFloat() + "");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).doWork();
        logService.stop();
        ThreadUtil.sleep(100);
    }
}