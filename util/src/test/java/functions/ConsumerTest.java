package functions;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * author : anger
 * date : 2022/7/9 19:19
 * description : Consumer 用法测试
 * 接收任意类型对象
 * 没有返回
 * 如果需要访问对象并对其执行某些操作，可以使用该函数
 */
class ConsumerTest {

    Consumer<String> toLower = s -> {
        System.out.println(s.toLowerCase());
    };

    Consumer<String> toUpper = s -> {
        System.out.println(s.toUpperCase());
    };

    @Test
    void testConsumer() {
        toUpper.andThen(toLower).accept("AaBbCc");
    }
}
