package adapter;

import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/8/6 16:34
 * description : 使用适配器模式
 * Enumeration 类型对象
 * 接收 Iterator 对象
 */
class IteratorAdapterTest {

    @Test
    void adaptingIteratorToEnumerationTest() {
        Iterator<Integer> iterator =
            IntStream.rangeClosed(0, 10)
                .boxed()
                .iterator();

        IteratorAdapter adapter = new IteratorAdapter(iterator);
        while (adapter.hasMoreElements())
            System.out.println(adapter.nextElement());

        assertInstanceOf(Enumeration.class, adapter);
    }

}