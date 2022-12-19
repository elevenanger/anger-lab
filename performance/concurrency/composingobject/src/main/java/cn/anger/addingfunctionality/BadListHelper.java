package cn.anger.addingfunctionality;

import cn.anger.annotation.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : anger
 */
@NotThreadSafe
public class BadListHelper<E> {
    public List<E> list =
        Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E element) {
        boolean isAbsent = !list.contains(element);
        if (isAbsent)
            list.add(element);
        return isAbsent;
    }
}