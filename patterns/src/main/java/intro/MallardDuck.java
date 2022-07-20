package intro;

import intro.behaviors.implementations.FlyWithWings;
import intro.behaviors.implementations.Quack;

/**
 * @author Anger
 * created on 2022/7/20
 * 野鸭
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        /*
        MallardDuck 将叫喊的行为委托给 Quack 类型对象
        将飞行的行为委托给 FlyWithWings 类型对象
         */
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }
}
