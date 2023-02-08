package usingconditionqueues;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;

import java.util.function.Predicate;

/**
 * @author : anger
 * 一个状态队列可能关联多个条件谓词
 * 多个线程同步同一个状态队列
 * 在执行相应操作之前必须检查对应的条件谓词
 */
@ThreadSafe
public class WakeUpTooSoon {

    private static final Predicate<Integer> isEven = i -> i % 2 == 0;
    @GuardedBy("this")
    protected int number;

    public WakeUpTooSoon(int number) {
        this.number = number;
    }

    public void oddNumberBroadcast() throws InterruptedException {
        // 同步 this 锁
        synchronized (this) {
            while (number > 0) {
                // 执行操作之前循环检查条件谓词
                // 条件谓词检查的变量由与状态队列相关的锁守护
                while (isEven.test(number))
                    // 检查条件谓词之后,调用 wait 方法
                    wait();
                System.out.println("odd number => " + number--);
                notifyAll();
            }
        }
    }

    public void evenNumberBroadcast() throws InterruptedException {
        synchronized (this) {
            while (number > 0) {
                while (isEven.negate().test(number))
                    wait();
                System.out.println("even number => " + number--);
                notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WakeUpTooSoon wakeUpTooSoon = new WakeUpTooSoon(100);

        Thread oddBro = new Thread(() -> {
            try {
                wakeUpTooSoon.oddNumberBroadcast();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread evenBro =
        new Thread(() -> {
            try {
                wakeUpTooSoon.evenNumberBroadcast();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        oddBro.start();
        evenBro.start();

        oddBro.join();
        evenBro.join();

    }


}
