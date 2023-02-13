package nonblockingalgorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : anger
 */
class LinkedQueueTest {

    @Test
    void testPut() {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        assertTrue(queue.put(10));
    }

}