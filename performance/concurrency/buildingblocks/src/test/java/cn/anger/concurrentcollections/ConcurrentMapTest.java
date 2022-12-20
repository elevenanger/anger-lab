package cn.anger.concurrentcollections;

import cn.anger.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author : anger
 */
public class ConcurrentMapTest {

    void concurrentMapAtomicCompoundOperations(ConcurrentMap<Integer, String> map) {
        map.putIfAbsent(1, "one");
        System.out.println(map);
        map.replace(1, "One");
        System.out.println(map);
        map.replace(1, "One", "ONE");
        System.out.println(map);
        map.computeIfAbsent(2, String::valueOf);
        System.out.println(map);
        map.computeIfPresent(1, (k, oldVal) -> k + ":" + oldVal);
        System.out.println(map);
    }

    @Test
    void testConcurrentHashMap() {
        ConcurrentHashMap<Integer, String> map =
            new ConcurrentHashMap<>();
        concurrentMapAtomicCompoundOperations(map);
    }

    @Test
    void testConcurrentMapIteration() {
        /*
        ConcurrentMap 在迭代器迭代的时候可以向集合添加元素
        不会抛出异常
        并且会反映对集合所做的修改
         */
        ConcurrentHashMap<Integer, String> map =
            new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++)
            map.put(i, " " + i + " ");

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ThreadUtil.sleep(10);
                map.putIfAbsent(10 + i, 10 + i + "");
            }
        }).start();

        map.forEach((k, v) -> {
            ThreadUtil.sleep(10);
            System.out.println(k + " : " + v);
        });
    }
}
