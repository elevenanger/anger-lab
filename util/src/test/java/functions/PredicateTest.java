package functions;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/7/9 18:30
 * description : 测试 Predicate 用法
 */
class PredicateTest {

    Predicate<String> shorterThan10 = s -> s.length() < 10;

    Predicate<String> containsI = s -> s.contains("i");
    @Test
    void testString() {
        String testString = "I love coding";
        assertFalse(shorterThan10.test(testString));
        assertTrue(containsI.test(testString));
        // 否运算
        assertTrue(shorterThan10.negate().test(testString));
        // predicate 组合
        assertTrue(shorterThan10.or(containsI).test(testString));
        assertFalse(shorterThan10.and(containsI).test(testString));
        assertTrue(shorterThan10.negate().and(containsI).test(testString));
        // 静态方法，比较两个对象是否指向同一个对象引用 Objects.equals()
        assertFalse(Predicate.isEqual("I don't love coding").test(testString));
    }
}
