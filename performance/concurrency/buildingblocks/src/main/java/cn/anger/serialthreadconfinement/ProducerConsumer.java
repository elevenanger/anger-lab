package cn.anger.serialthreadconfinement;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author : anger
 */
public class ProducerConsumer {
    private static final int BOUND = 10;
    private static final int N_CONSUMERS =
        Runtime.getRuntime().availableProcessors();

    public static void startIndexing(File ... roots) {
        BlockingQueue<File> queue = new LinkedBlockingDeque<>(BOUND);
        FileFilter fileFilter = f -> true;

        for (File root : roots)
            new Thread(new FileCrawler(queue, fileFilter, root)).start();

        for (int i = 0; i < N_CONSUMERS; i++)
            new Thread(new Indexer(queue)).start();

    }
}
