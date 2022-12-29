package taskcancellation;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @author : anger
 * 质数生产者
 */
public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> primesQueue;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> primesQueue) {
        this.primesQueue = primesQueue;
    }

    @Override
    public void run() {
       try {
           BigInteger p = BigInteger.ONE;
           while (!cancelled) {
               p = p.nextProbablePrime();
               System.out.println("put prime => " + p.intValue());
               primesQueue.put(p);
           }
       } catch (InterruptedException e) {
           //do nothing
       }
    }

    public void cancel() {
        System.out.println("receive cancel request, cancelling");
        cancelled = true;
    }

}
