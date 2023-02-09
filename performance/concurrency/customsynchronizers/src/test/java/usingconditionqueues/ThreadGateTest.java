package usingconditionqueues;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author : anger
 */
class ThreadGateTest {

    @Test
    void testThreadGate() {
        ThreadGate gate = new ThreadGate();
        Stream.generate(() -> new WaitGateThread(gate))
            .limit(10)
            .forEach(WaitGateThread::start);
        System.out.println("open gate");
        gate.open();
        gate.close();
        Stream.generate(() -> new WaitGateThread(gate))
            .limit(10)
            .forEach(WaitGateThread::start);
        System.out.println("open gate");
        gate.open();
    }

    static class WaitGateThread extends Thread {
        private final ThreadGate gate;

        public WaitGateThread(ThreadGate gate) {
            this.gate = gate;
        }

        @Override
        public void run() {
            try {
                gate.await();
                System.out.println(Thread.currentThread().getId());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}