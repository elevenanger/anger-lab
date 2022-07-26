package intro.behaviors.implementations;

import intro.behaviors.FlyBehavior;

/**
 * @author Anger
 * created on 2022/7/20
 * 飞行行为实现类
 * 无法飞行
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("FlyNoWay.");
    }
}
