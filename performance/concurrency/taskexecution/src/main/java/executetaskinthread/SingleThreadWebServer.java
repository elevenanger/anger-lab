package executetaskinthread;

import cn.anger.util.net.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : anger
 * 单线程 SocketServer
 * 串行执行请求
 */
public class SingleThreadWebServer {
    public static void main(String [] args) throws IOException {
        try (ServerSocket server = new ServerSocket(8081)){
            while (true) {
                Socket connection = server.accept();
                SocketUtil.handleConnection(connection, "single thread server", 2);
            }
        }
    }

}
