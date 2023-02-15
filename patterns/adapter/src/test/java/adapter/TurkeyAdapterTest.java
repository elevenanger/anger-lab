package adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/8/5 21:09
 * description : 适配器模式测试
 */
class TurkeyAdapterTest {

    @Test
    void testTurkeyAdapter() {

        Turkey turkey = new WildTurkey();
        TurkeyAdapter turkeyAdapter = new TurkeyAdapter(turkey);

        turkeyAdapter.quack();
        turkeyAdapter.fly();

        assertInstanceOf(Duck.class, turkeyAdapter);
    }
}