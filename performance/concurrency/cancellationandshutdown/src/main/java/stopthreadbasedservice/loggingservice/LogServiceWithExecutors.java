package stopthreadbasedservice.loggingservice;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 */
public class LogServiceWithExecutors {
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private final PrintWriter writer;

    public LogServiceWithExecutors(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void stop() throws InterruptedException {
        try {
            exec.shutdown();
            exec.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } finally {
            writer.close();
        }
    }

    public void log(String msg) {
        exec.execute(new WriteTask(msg));
    }

    private class WriteTask implements Runnable {
        private final String msg;

        public WriteTask(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            writer.write(msg + "\n");
        }
    }
}
