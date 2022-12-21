package cn.anger.synchronizers.semaphores;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @author : anger
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        set = Collections.synchronizedSet(new HashSet<>());
        // 初始化许可数量
        semaphore = new Semaphore(bound);
    }

    public boolean add(T element) throws InterruptedException {
        // 获取一个许可证
        semaphore.acquire();
        boolean wasAdded = false;
        try {
            // 添加成功则占用一个许可证
            wasAdded = set.add(element);
            return wasAdded;
        } finally {
            // 添加失败则释放刚刚获取的许可证
            if (!wasAdded)
                semaphore.release();
        }
    }

    public boolean remove(Object o) {
        // 获取 set 移除元素的结果
        boolean wasRemoved = set.remove(o);
        // 如果移除成功则释放一个许可证
        if (wasRemoved)
            semaphore.release();
        return wasRemoved;
    }
}
