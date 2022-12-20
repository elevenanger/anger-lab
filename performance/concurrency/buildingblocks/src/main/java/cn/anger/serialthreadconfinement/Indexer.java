package cn.anger.serialthreadconfinement;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @author : anger
 * 文件索引，消费队列中的文件数据
 */
public class Indexer implements Runnable {
    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true)
                indexFile(queue.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void indexFile(File file) {
        System.out.println(file);
    }
}
