package executorframework;

import cn.anger.net.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author : anger
 * 使用 Executor 实现一个 web 服务器
 * 执行客户端提交的任务
 */
public class TaskExecutionWebServer {
    private static final int N_THREADS = 100;
    // 创建一个指定线程数量的线程池
    private static final Executor exec =
        Executors.newFixedThreadPool(N_THREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8083);
        while (true) {
            final Socket connection = server.accept();
            // 主线程提交任务，线程池中的线程消费任务执行任务逻辑
            exec.execute(() ->
                SocketUtil.handleConnection(connection, "Task execution server", 5));
        }
    }
}
