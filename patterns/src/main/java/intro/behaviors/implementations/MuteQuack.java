package intro.behaviors.implementations;

import intro.behaviors.QuackBehavior;

/**
 * @author Anger
 * created on 2022/7/20
 * 无法发出叫声的实现
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("MuteQuack.") ;
    }
}
