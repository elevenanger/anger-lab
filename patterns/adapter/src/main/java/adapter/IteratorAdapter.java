package adapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * author : anger
 * date : 2022/8/6 16:32
 * description : Iterator 适配器
 * 使用 Enumeration 适配 Iterator
 */
public class IteratorAdapter implements Enumeration<Object> {

    private final Iterator<?> iterator;

    public IteratorAdapter(Iterator<?> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public Object nextElement() {
        return iterator.next();
    }
}
