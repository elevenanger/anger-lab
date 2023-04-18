package executorframework;

import cn.anger.util.concurrency.ThreadUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            // 检查 exec 状态
            while (!exec.isShutdown()) {
                try {
                    final Socket connection = server.accept();
                    exec.execute(() -> {
                            ThreadUtil.sleep(5);

                            System.out.printf("Request from => %s, handled by => %s%n",
                                connection.getInetAddress().getHostAddress(),
                                Thread.currentThread().getName());

                            try (
                                BufferedReader reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                                PrintWriter writer = new PrintWriter(connection.getOutputStream())){
                                reader.lines()
                                    .forEach(line -> {
                                        if (line.contains("quit"))
                                            stop();
                                        System.out.println(line);
                                    });
                                writer.write("Life Cycle Web Server");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                } catch (RejectedExecutionException e) {
                    if (!exec.isShutdown())
                        System.err.printf("task submission rejected %s", e);
                }
            }
        }
    }

    public void stop() {
        System.out.println("shutting down...");
        exec.shutdown();
    }

    public static void main(String[] args) throws IOException {
        new LifeCycleWebServer().start();
    }
}
