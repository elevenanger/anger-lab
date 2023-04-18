package executetaskinthread;

import cn.anger.util.net.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : anger
 * 每个请求都分配一个独立的线程进行处理
 * 主线程可以继续接收请求
 * 并行执行服务端处理逻辑
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket socket = new ServerSocket(8082)){
            while (true) {
                final Socket connection = socket.accept();
                new Thread(() ->
                    SocketUtil.handleConnection(connection, "thread per request", 2))
                    .start();
            }
        }
    }
}
