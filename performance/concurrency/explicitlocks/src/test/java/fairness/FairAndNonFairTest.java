package fairness;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class FairAndNonFairTest {

    @Test
    void testFairAndUnfair() {
        FairAndNonFair fair = new FairAndNonFair(new ReentrantLock(true));
        FairAndNonFair nonFair = new FairAndNonFair(new ReentrantLock(false));

        ConcurrentWorkStream.heavyWorkStream(fair::process).doWork();
        ConcurrentWorkStream.heavyWorkStream(nonFair::process).doWork();
    }

}