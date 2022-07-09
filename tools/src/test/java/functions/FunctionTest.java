package functions;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/7/9 19:35
 * description : Function 用法
 * 接收一个泛型 T 类型对象
 * 返回一个 R 类型泛型对象
 * 如果需要对输入对象进行映射操作
 * 可以使用该函数
 */
class FunctionTest {
    String one = "1";
    String two = "2";
    Function<String, String> appendOne = s -> Strings.concat(s, one);

    Function<String, String> appendTwo = s -> Strings.concat(s, two);

    @Test
    void testFunction() {
        String str = "order";
        System.out.println();
        // compose 前置函数
        assertEquals("order21", appendOne.compose(appendTwo).apply(str));
        // andThen 后置函数
        assertEquals("order12", appendOne.andThen(appendTwo).apply(str));
        assertEquals(str, Function.identity().apply(str));
    }
}
