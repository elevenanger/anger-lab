package duck.decorator;

import duck.ability.Quackable;
import duck.observer.Observer;

/**
 * author : anger
 * date : 2022/8/16 09:27
 * description : 统计鸭子的叫声数量
 */
public class QuackCounter implements Quackable {
    private final Quackable duck;

    private static int quackCount;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        quackCount ++;
    }

    public static int getQuackCount() {
        return quackCount;
    }

    @Override
    public void registerObserver(Observer observer) {
        duck.registerObserver(observer);
    }

    @Override
    public void notifyObserver() {
        duck.notifyObserver();
    }
}
