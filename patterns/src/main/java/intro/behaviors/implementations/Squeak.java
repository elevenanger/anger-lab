package intro.behaviors.implementations;

import intro.behaviors.QuackBehavior;

/**
 * @author Anger
 * created on 2022/7/20
 * 鸭叫行为实现类
 * 吱吱叫
 * 橡胶鸭叫声
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak.");
    }
}
