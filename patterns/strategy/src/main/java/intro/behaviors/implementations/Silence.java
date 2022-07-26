package intro.behaviors.implementations;

import intro.behaviors.QuackBehavior;

/**
 * @author Anger
 * created on 2022/7/25
 */
public class Silence implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Silence");
    }
}
