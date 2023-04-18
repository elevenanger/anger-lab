package taskcancellation.noninterruptibleblock;

import cn.anger.util.net.SocketUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : anger
 */
class ReaderThreadTest {
    @Test
    void setUpServer() throws IOException {
        try (ServerSocket server = new ServerSocket(8081)){
            while (true) {
                Socket connection = server.accept();
                ReaderThreadUtil.handleConnection(connection);
            }
        }
    }

    @Test
    void testReader() {
        SocketUtil.socketClient("localhost", 8081, "test");
    }
}