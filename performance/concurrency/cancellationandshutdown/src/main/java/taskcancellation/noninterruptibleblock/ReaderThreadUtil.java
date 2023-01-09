package taskcancellation.noninterruptibleblock;

import java.io.IOException;
import java.net.Socket;

/**
 * @author : anger
 */
public class ReaderThreadUtil {

   private ReaderThreadUtil() {}

    public static void handleConnection(Socket connection) throws IOException {
        ReaderThread reader = new ReaderThread(connection);
        reader.start();
    }

}
