package intro;

import intro.behaviors.implementations.FlyNoWay;
import intro.behaviors.implementations.Silence;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class ModelDuck extends Duck{
    public ModelDuck() {
        this.flyBehavior = new FlyNoWay();
        this.quackBehavior = new Silence();
    }

    @Override
    public void display() {
        System.out.println("model duck");
    }
}
