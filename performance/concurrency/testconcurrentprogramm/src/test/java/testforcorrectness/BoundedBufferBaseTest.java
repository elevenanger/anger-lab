package testforcorrectness;

import cn.anger.concurrency.ThreadUtil;
import cn.anger.dump.DumpUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : anger
 */
class BoundedBufferBaseTest {
    private static final int LOCKUP_DETECT_TIMEOUT = 100;
    private static final int CAPACITY = 10_000;

    /**
     * 串行测试
     * 在开始并发测试之前识别哪些问题和并发无关
     */
    @Test
    void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(1);
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(1);
        buffer.put(10);
        assertFalse(buffer.isEmpty());
        assertTrue(buffer.isFull());
    }

    /**
     * 测试一个方法是否阻塞
     */
    @Test
    void testTakeBlockWhenEmpty() {
        final BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);

        Thread taker = new Thread(() -> {
            try {
                // 会发生阻塞的操作
                int unused = buffer.take();
                // 如果执行到这一步说明线程没有阻塞，测试失败，打印失败记录
                fail();
                // 走到这一步则视为成功,什么都不做，结束线程
            } catch (InterruptedException success) {}
        });

        try {
            // 在单独的线程中执行会发生阻塞的操作
            taker.start();
            // 设置超时时间，等待这个线程发生阻塞
            ThreadUtil.sleep(LOCKUP_DETECT_TIMEOUT);
            // 中断阻塞的线程
            taker.interrupt();
            // 主线程测试方式，测试阻塞线程 join 方法
            // 如果线程响应了中断
            // 则 join 方法很快就能执行完成
            // 通过 taker.isAlive() 校验 join 方法返回成功
            // 使用定时的 join 防止主线程因为别的原因无限制的等待下去
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testUnblockWhenBufferHasElement() {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);

        Thread t = new Thread(() -> {
            try {
                int unused = buffer.take();
            } catch (InterruptedException e) {}
        });

        try {
            t.start();
            // 没有消费到元素的时候线程发生阻塞
            ThreadUtil.sleep(LOCKUP_DETECT_TIMEOUT);
            // 主线程往队列中 put 元素
            // 消费线程消费到元素后执行代码，阻塞终止，线程结束
            buffer.put(100);
            // 判断线程是否正常结束
            t.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(t.isAlive());
        } catch (InterruptedException e) {
            fail();
        }
    }

    class Big { double[] data = new double[100_000]; }

    @Test
    void testLeak() throws InterruptedException{
        BoundedBuffer<Big> buffer = new BoundedBuffer<>(CAPACITY);
        final Function<String, Long> dumpAndGetSize = filename -> {
            try {
                DumpUtil.dumpHeap(filename, true);
                return Paths.get(DumpUtil.COMMON_DIR, filename).toFile().length();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        long heapSize1 = dumpAndGetSize.apply("dumpFile1.hprof");
        for (int i = 0; i < CAPACITY; i++)
            buffer.put(new Big());

        for (int i = 0; i < CAPACITY; i++)
            buffer.take();

        System.gc();

        long heapSize2 = dumpAndGetSize.apply("dumpFile2.hprof");

        assertTrue(Math.abs(heapSize1 - heapSize2) < 10_000 * 1024);
    }

    private void fail() {
        System.out.println("test failed.");
    }

}