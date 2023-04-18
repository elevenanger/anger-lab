package labutils.concurrentcollections;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : anger
 */
public class TestCopyOnWriteCollections {

    @Test
    void testIterator() {
        // CopyOnWriteList 可以在迭代的时候修改元素
        // 但是不会影响迭代器的迭代过程
        // 创建迭代器的时候就已经确定需要迭代的元素
        CopyOnWriteArrayList<Integer> list =
            new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++)
            list.addIfAbsent(i);

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ThreadUtil.sleep(10);
                list.remove(9 - i);
            }
        }).start();

        list.forEach(v -> {
            ThreadUtil.sleep(10);
            System.out.print(v + " ");
        });

        System.out.println("\n" + list);
    }
}
