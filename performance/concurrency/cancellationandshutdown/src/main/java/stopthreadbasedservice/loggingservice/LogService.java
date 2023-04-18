package stopthreadbasedservice.loggingservice;

import labutils.annotation.GuardedBy;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : anger
 * 日志记录器
 * 提供日志记录器的终止方法
 * 终止记录日志的线程
 */
public class LogService {
    // 存储日志消息的队列
    private final BlockingQueue<String> queue;
    // 日志记录线程
    private final LoggerThread thread;
    // 记录器
    private final PrintWriter writer;
    // shutdown 标识
    @GuardedBy("this")
    private boolean isShutdown;
    // 日志数量
    @GuardedBy("this")
    private int reservations;

    public LogService(Writer writer) {
        this.queue = new LinkedBlockingQueue<>();
        this.thread = new LoggerThread();
        this.writer = new PrintWriter(writer);
    }

    /**
     * 开启日志记录器
     */
    public void start() {
        thread.start();
    }

    /**
     * 终止日志记录器
     */
    public void stop() {
        synchronized (this) {
            // 将 shutdown 标识置为 true
            isShutdown = true;
        }
        // 终止日志记录线程
        thread.interrupt();
    }

    /**
     * 记录日志
     * @param msg 日志信息
     * @throws InterruptedException 中止异常
     */
    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            // 每次记录日志之前检查 shutdown 标识, 防止再写入新的日志导致队列消息无法被消费而阻塞
            if (isShutdown)
                throw new IllegalStateException("日志记录器已关闭");
            ++reservations;
        }
        queue.put(msg);
    }

    /**
     * 日志记录线程
     */
    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            // 如果 shutdown 标识为 true 并且未写入日志为 0 则跳出循环
                            if (isShutdown && reservations == 0)
                                break;
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                        writer.flush();
                    } catch (InterruptedException e) {
                    }
                }
            } finally {
                writer.close();
            }
        }
    }
}
