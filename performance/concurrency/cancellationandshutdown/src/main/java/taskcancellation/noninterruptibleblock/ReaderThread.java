package taskcancellation.noninterruptibleblock;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author : anger
 * 处理不响应 interrupt 请求的代码块
 */
public class ReaderThread extends Thread {
    private static final int BUFF_SIZE = 1024;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    /**
     * 重写 interrupt 方法
     * 调用 socket close 方法关闭 socket
     */
    @Override
    public void interrupt() {
        try {
            System.out.println("close connection...");
            socket.close();
        } catch (IOException e) {}
        finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFF_SIZE];
            while (true) {
                int count = in.read(buf);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuf(buf, count);
            }
        } catch (IOException e) {
            interrupt();
        }
    }

    private void processBuf(byte[] buf, int count) {
        System.out.printf("%s read %d byte data.%n", Thread.currentThread().getName(), count);
        String msg = new String(buf);
        System.out.println(msg);
        if (msg.contains("test"))
            interrupt();
    }
}
