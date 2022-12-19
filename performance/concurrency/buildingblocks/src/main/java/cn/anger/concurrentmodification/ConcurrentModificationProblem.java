package cn.anger.concurrentmodification;

import cn.anger.concurrency.ThreadUtil;

import java.util.List;

/**
 * @author : anger
 */
public class ConcurrentModificationProblem {
    public static void forEachIteration(List list) {
        for (Object o : list) {
            ThreadUtil.sleep(10);
            System.out.println(o.toString());
        }
    }

    public static void synchronizedForEachIteration(List list) {
        synchronized (list) {
            for (Object o : list) {
                ThreadUtil.sleep(10);
                System.out.println(o.toString());
            }
        }
    }

    public static void forEachInnerIteration(List list) {
        list.forEach(o -> {
            ThreadUtil.sleep(10);
            System.out.println(o.toString());
        });
    }
}
