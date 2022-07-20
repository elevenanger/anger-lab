package intro.behaviors.implementations;

import intro.behaviors.FlyBehavior;

/**
 * @author Anger
 * created on 2022/7/20
 * 飞行行为实现类
 * 用翅膀飞行
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("FlyWithWings.fly");
    }
}
