package usingconditionqueues;

import labutils.annotation.GuardedBy;
import labutils.annotation.ThreadSafe;

/**
 * @author : anger
 * 可重复打开关闭的 latch 类
 */
@ThreadSafe
public class ThreadGate {
    @GuardedBy("this")
    private boolean isOpen;
    @GuardedBy("this")
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    /**
     * 条件谓词
     * isOpen || arrivalGeneration != generation
     * 达成这个条件则程序可以继续进行
     */
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation)
            wait();
    }
}
