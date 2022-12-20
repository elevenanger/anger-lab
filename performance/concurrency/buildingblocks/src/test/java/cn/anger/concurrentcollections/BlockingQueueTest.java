package cn.anger.concurrentcollections;

import cn.anger.concurrency.ConcurrentWorkStream;
import cn.anger.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
public class BlockingQueueTest {
    final Random random = new Random();

    final Integer capacity = 200;

    void producerConsumerPattern(BlockingQueue<Integer> queue) {
        AtomicInteger lineNo = new AtomicInteger(1);

        ConcurrentWorkStream.commonWorkStreams(
            () -> {
                System.out.println(lineNo.getAndIncrement() + " producing...");
                ThreadUtil.sleep(1);
                if (!queue.offer(random.nextInt(200))){
                    System.out.println(lineNo.getAndIncrement() + " queue is full...");
                };
            },
            () -> {
                System.out.println(lineNo.getAndIncrement() + " consuming...");
                ThreadUtil.sleep(2);
                try {
                    Integer polled = queue.poll(10, TimeUnit.MILLISECONDS);
                    if (polled != null) {
                        System.out.println(lineNo.getAndIncrement() + " " + polled);
                    } else {
                        System.out.println(lineNo.getAndIncrement() + " " +
                            Thread.currentThread().getName() +
                            " consuming time out.");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        ).doWork();
    }

    @Test
    void testLinkedBlockingQueue() {
        producerConsumerPattern(new LinkedBlockingQueue<>(capacity));
    }

    @Test
    void testArrayBlockingQueue() {
        producerConsumerPattern(new ArrayBlockingQueue<>(capacity));
    }

    @Test
    void testPriorityBlockingQueue() {
        producerConsumerPattern(new PriorityBlockingQueue<>(capacity));
    }
}
