package mock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : anger
 */
public class MockApp {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8081);
        Socket socket1 = socket.accept();
    }
}
