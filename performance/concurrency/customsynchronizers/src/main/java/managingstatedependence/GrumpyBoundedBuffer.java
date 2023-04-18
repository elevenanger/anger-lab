package managingstatedependence;

import labutils.annotation.ThreadSafe;
import cn.anger.util.concurrency.ThreadUtil;
import cn.anger.util.random.Random;

/**
 * @author : anger
 * 同步 get 和 put 方法
 * 当状态不满足时抛出异常
 */
@ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    synchronized void put(V v) throws BufferFullException {
        if (isFull())
            throw new BufferFullException();
        doPut(v);
    }

    synchronized V take() throws BufferEmptyException {
        if (isEmpty())
            throw new BufferEmptyException();
        return doTake();
    }

    public static class ExampleUsage {
        private final GrumpyBoundedBuffer<Integer> buffer =
            new GrumpyBoundedBuffer<>(1);

        private static final int SLEEP_TIME = 50;

        void useBuffer() {
            while (true) {
                try {
                    // 获取元素
                    Integer v = buffer.take();
                    if (v == null)
                        System.out.println("null");
                    if (v == Random.getRandom())
                        // 使用元素
                        System.out.println(v);
                    break;
                } catch (BufferEmptyException e) {
                    ThreadUtil.sleep(SLEEP_TIME);
                }
            }
        }

        void putBuffer() {
            while (true) {
                try {
                    buffer.put(Random.getRandom());
                    break;
                } catch (BufferFullException e) {
                    ThreadUtil.sleep(SLEEP_TIME);
                }
            }
        }
    }
}
