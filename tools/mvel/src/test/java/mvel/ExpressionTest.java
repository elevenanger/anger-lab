package mvel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mvel2.MVEL;

/**
 * author : anger
 * date : 2022/9/29 13:05
 * description : 表达式测试类
 */
@Slf4j
class ExpressionTest {

    @Test
    void testString() {
        String res = MVEL.evalToString("1 + 2");
        log.info(res);
    }

}
