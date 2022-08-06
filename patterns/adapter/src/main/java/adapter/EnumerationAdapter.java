package adapter;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * author : liuanglin
 * date : 2022/8/6 15:56
 * description : 迭代适配器
 * 实现 Iterator 接口
 * 适配（组合） Enumeration 对象
 */
public class EnumerationAdapter<T> implements Iterator<T> {

    private final Enumeration<T> enumeration;

    public EnumerationAdapter(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        if (! hasNext())
            throw new NoSuchElementException();
        return enumeration.nextElement();
    }

    /**
     * 适配器模式中
     * 无法实现的被适配者的方法
     * 抛出未实现的操作异常
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
