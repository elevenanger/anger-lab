package adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/8/5 21:26
 * description : Duck 适配器测试
 */
class DuckAdapterTest {

    @Test
    void testDuckAdapter() {
        Duck duck = new MallardDuck();
        DuckAdapter duckAdapter = new DuckAdapter(duck);

        duckAdapter.fly();
        duckAdapter.gobble();

        assertInstanceOf(Turkey.class, duckAdapter);
    }

}