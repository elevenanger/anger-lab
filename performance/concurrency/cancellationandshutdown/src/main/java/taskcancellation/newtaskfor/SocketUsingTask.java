package taskcancellation.newtaskfor;

import labutils.annotation.GuardedBy;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author : anger
 * 中断 socket 任务
 * 实现 CancellableTask 接口
 * 定义 Future.cancel 方法关闭 socket
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {
    private static final int BUFF_SIZE = 512;
    @GuardedBy("this")
    private Socket socket;

    protected synchronized void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * 调用 socket.close
     * 中断 socket
     */
    public synchronized void cancel() {
        System.out.println("cancelling task...");
        try {
            if (socket != null)
                socket.close();
        } catch (IOException e) {}
    }

    /**
     * 定义 Future.cancel 方法
     * 调用 cancel 方法
     * @return RunnableFuture 任务
     */
    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }

    protected void readInput() throws IOException {
        try (InputStream in = socket.getInputStream()) {
            byte[] buff = new byte[BUFF_SIZE];
            while (true) {
                int count = in.read(buff);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuf(buff, count);
            }
        }
    }

    private void processBuf(byte[] buf, int count) {
        System.out.printf("%s read %d byte data.%n", Thread.currentThread().getName(), count);
        String msg = new String(buf);
        System.out.println(msg);
        if (msg.contains("test"))
            cancel();
    }
}
