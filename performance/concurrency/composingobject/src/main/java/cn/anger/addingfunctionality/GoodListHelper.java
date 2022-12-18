package cn.anger.addingfunctionality;

import cn.anger.annotation.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : anger
 */
@ThreadSafe
public class GoodListHelper<E> {
    public List<E> list =
        Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E element) {
        synchronized (list) {
            boolean isAbsent = !list.contains(element);
            if (isAbsent)
                list.add(element);
            return isAbsent;
        }
    }
}
