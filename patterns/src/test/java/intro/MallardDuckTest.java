package intro;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


/**
 * @author Anger
 * created on 2022/7/20
 */
@Slf4j
class MallardDuckTest {

    @Test
    void testBehavior() {
        log.info("MallardDuck behavior test");
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();
    }
}