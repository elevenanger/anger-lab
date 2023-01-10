package stopthreadbasedservice.poisonpill;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 * 使用可识别的对象终止 生产者-消费者 服务
 */
public class IndexingService {
    private static final int CAPACITY = 1000;
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;
    private final Set<File> indexedFile = new HashSet<>();

    public IndexingService(FileFilter fileFilter, File root) {
        this.root = root;
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        // 递归遍历目录中的所有文件
        this.fileFilter = file -> file.isDirectory() || fileFilter.accept(file);
    }

    /**
     * 是否已经为文件创建索引标识
     * @param file 文件
     * @return false
     */
    private boolean alreadyIndexed(File file) {
        return indexedFile.contains(file);
    }

    /**
     * 生产者线程
     * 遍历目录中的所有文件
     * 将其放入队列中
     */
    class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                while (true) {
                    try {
                        // 遍历完成之后，将 终止标识 放入队列中
                        queue.put(POISON);
                    } catch (InterruptedException e) {}
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory())
                        crawl(entry);
                    else if (!alreadyIndexed(entry))
                        queue.put(entry);
                }
            }
        }
    }

    class IndexerThread extends Thread {
        private final AtomicInteger count = new AtomicInteger(0);
        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    // 如果读取到终止标识则停止消费
                    if (file == POISON)
                        break;
                    else
                        indexFile(file);
                }
            } catch (InterruptedException consumed) {}
        }

        public void indexFile(File file) {
            System.out.printf("index file %d => %s%n", count.incrementAndGet(), file.getAbsolutePath());
            indexedFile.add(file);
        }
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }
}
