package introduction;

import cn.anger.ch01.introduction.SafeSequence;
import cn.anger.ch01.introduction.UnsafeSequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
public class IntroductionTests {

    @Test
    void testUnsafe() {
        UnsafeSequence sequence = new UnsafeSequence();

        Runnable r = () -> {
            for (int i = 0; i < 10_000; i++) {
                sequence.getNext();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertNotEquals(sequence.getNext(), 20_000);
    }

    @Test
    void testSafe() {
        SafeSequence sequence = new SafeSequence();

        Runnable r = () -> {
            for (int i = 0; i < 10_000; i++) {
                sequence.getNext();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(sequence.getNext(), 20_000);
    }
}
