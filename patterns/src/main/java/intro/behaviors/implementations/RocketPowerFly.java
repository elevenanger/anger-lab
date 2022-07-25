package intro.behaviors.implementations;

import intro.behaviors.FlyBehavior;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class RocketPowerFly implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("flying with rocket!");
    }
}
