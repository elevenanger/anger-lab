package cn.anger.concurrentcollections;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author : anger
 */
public class QueueTest {
    final Random random = new Random();

    void addTen(Queue queue) {
        for (int i = 0; i < 10; i++) {
            queue.add(random.nextInt(1000));
        }
    }

    void queOperations(Queue queue) {
        addTen(queue);
        System.out.println(queue);

        while (!queue.isEmpty())
            System.out.print(queue.poll() + " ");
    }

    @Test
    void testPriorityQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue();
        queOperations(queue);
    }

    @Test
    void testConcurrentLinkedQueue() {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queOperations(queue);
    }

    @Test
    void testArrayBlockingQueue() {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        queOperations(queue);
    }

    @Test
    void testLinkedTransferQueue() {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();
        queOperations(queue);
    }

    @Test
    void testConcurrentLinkedDeque() {
        ConcurrentLinkedDeque<Integer> deque = new ConcurrentLinkedDeque<>();
        queOperations(deque);
    }

}