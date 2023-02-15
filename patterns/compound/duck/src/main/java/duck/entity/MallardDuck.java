package duck.entity;

import duck.ability.Quackable;
import duck.observer.Observable;
import duck.observer.Observer;
import duck.observer.QuackObservable;

/**
 * author : anger
 * date : 2022/8/16 08:25
 */
public class MallardDuck implements Quackable {
    private final QuackObservable observable;

    public MallardDuck() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("MallardDuck.quack");
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObserver() {
        observable.notifyObserver();
    }
}
