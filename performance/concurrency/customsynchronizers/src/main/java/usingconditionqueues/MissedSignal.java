package usingconditionqueues;

import cn.anger.concurrency.ConcurrentWorkStream;

import java.util.Random;

/**
 * @author : anger
 * 信号丢失
 * 线程将会永远等待一个已经发生的事件
 */
public class MissedSignal extends WakeUpTooSoon {
    private final int luckyNumber;
    public MissedSignal(int number) {
        super(number);
        luckyNumber = new Random().nextInt(number -1 ) + 1;
    }

    public void luckyCount() throws InterruptedException {
        synchronized (this) {
            while (number != luckyNumber)
                wait();
            System.out.println("Lucky number =>" + number--);
            notifyAll();
        }
    }

    public static void main(String[] args) {
        MissedSignal missedSignal = new MissedSignal(100);

        ConcurrentWorkStream.workerMatchingTask(
            () -> {
                try {
                    missedSignal.oddNumberBroadcast();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            },
            () -> {
                try {
                    missedSignal.evenNumberBroadcast();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            },
            () -> {
                try {
                    missedSignal.luckyCount();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            },
            () -> {
                try {
                    missedSignal.luckyCount();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        ).doWork();
    }
}
