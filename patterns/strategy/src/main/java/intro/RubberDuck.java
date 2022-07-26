package intro;

import intro.behaviors.implementations.FlyNoWay;
import intro.behaviors.implementations.Squeak;

/**
 * @author Anger
 * created on 2022/7/20
 * 橡胶鸭
 */
public class RubberDuck extends Duck{
    public RubberDuck() {
        this.quackBehavior = new Squeak();
        this.flyBehavior = new FlyNoWay();
    }
}
