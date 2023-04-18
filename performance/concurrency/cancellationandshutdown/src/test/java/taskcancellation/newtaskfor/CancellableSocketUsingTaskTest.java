package taskcancellation.newtaskfor;

import cn.anger.util.net.SocketUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 */
class CancellableSocketUsingTaskTest {
    CancellingExecutor executor =
        new CancellingExecutor(1, 10, 1000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10));
    @Test
    void setUpServer() throws IOException, ExecutionException, InterruptedException {
        try (ServerSocket server = new ServerSocket(8081)){
            while (true) {
                Socket connection = server.accept();
                CancellableSocketUsingTask<Void> task = new CancellableSocketUsingTask<>();
                task.setSocket(connection);
                RunnableFuture<Void> future = executor.newTaskFor(task);
                future.run();
            }
        }
    }

    @Test
    void testCancellableSocket() {
        SocketUtil.socketClient("localhost", 8081, "test");
    }
}