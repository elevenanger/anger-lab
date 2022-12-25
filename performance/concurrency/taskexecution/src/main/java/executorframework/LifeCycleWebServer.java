package executorframework;

import cn.anger.net.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author : anger
 * 使用 ExecutorService 的生命周期特性来管理 server
 */
public class LifeCycleWebServer {

    private final ExecutorService exec = Executors.newWorkStealingPool();

    public void start() throws IOException {
        try (ServerSocket server = new ServerSocket(8084)) {
            while (!exec.isShutdown()) {
                try {
                    final Socket connection = server.accept();
                    exec.execute(() -> {
                        try {
                            if (SocketUtil.readCommand(connection.getInputStream(), "shutdown"))
                                stop();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        SocketUtil.handleConnection(connection, "Life Cycle Server", 5);
                    });
                } catch (RejectedExecutionException e) {
                    if (!exec.isShutdown())
                        System.err.printf("task submission rejected %s", e);
                }
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }

    public static void main(String[] args) throws IOException {
        new LifeCycleWebServer().start();
    }
}
