package testforcorrectness;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.Semaphore;

/**
 * @author : anger
 */
@ThreadSafe
public class BoundedBuffer<E> {
    // 可以从数组中移除的元素数量（数组中已有的元素数量）
    private final Semaphore availableItems;
    // 数组中可以插入的元素空间
    private final Semaphore availableSpaces;
    private final E[] items;
    private int putPosition = 0;
    private int takePosition = 0;

    public BoundedBuffer(int capacity) {
        // 可用元素数量，初始化为0
        availableItems = new Semaphore(0);
        // 可用空间初始化为数组长度
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    /**
     * 判断 buffer 是否为空
     * 判断可用的元素信号量
     * @return true 为空 false 不为空
     */
    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    /**
     * 判断 buffer 是否已满
     * 判断可用空间的信号量
     * @return true 已满 false 未满
     */
    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    /**
     * 往buffer中插入元素
     * availableSpace 减少
     * availableItem 增加
     * 如果 availableSpace 减少到 0 说明队列已满
     * 方法会阻塞直到获取到可用空间插入元素
     * @param element 元素
     * @throws InterruptedException
     */
    public void put(E element) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(element);
        availableItems.release();
    }

    /**
     * 从 buffer 获取元素
     * 元素数量减少
     * 可用空间增加
     * 如果 availableItem 减少到 0 说明队列为空
     * 方法会阻塞直到能够获取到可用元素
     * @return 获取的元素
     * @throws InterruptedException
     */
    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    /**
     * 执行插入操作
     * 原子操作操作
     * putPosition 自增
     * 到达上限从 0 开始
     * @param element 插入元素
     */
    private synchronized void doInsert(E element) {
        int i = putPosition;
        items[i] = element;
        putPosition = (++i == items.length)? 0: i;
    }

    /**
     * 从数组中提取元素
     * 原子操作
     * takePosition 自增
     * @return 元素
     */
    private synchronized E doExtract() {
        int i = takePosition;
        E element = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return element;
    }
}