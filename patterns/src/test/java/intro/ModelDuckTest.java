package intro;

import intro.behaviors.implementations.RocketPowerFly;
import intro.behaviors.implementations.Squeak;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author Anger
 * created on 2022/7/25
 */
@Slf4j
class ModelDuckTest {

    static Duck duck = new ModelDuck();

    @Test
    void routineBehavior() {
        log.info("routine behavior");
        duck.performFly();
        duck.performQuack();
    }

    @Test
    void dynamicBehavior() {
        duck.display();
        log.info("default setting behavior");
        duck.performQuack();
        duck.performFly();

        duck.setFlyBehavior(new RocketPowerFly());
        duck.setQuackBehavior(new Squeak());

        log.info("after dynamic change behavior");
        duck.performQuack();
        duck.performFly();
    }
}