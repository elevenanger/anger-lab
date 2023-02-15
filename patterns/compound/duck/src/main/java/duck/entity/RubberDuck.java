package duck.entity;

import duck.ability.Quackable;
import duck.observer.Observable;
import duck.observer.Observer;
import duck.observer.QuackObservable;

/**
 * author : anger
 * date : 2022/8/16 08:28
 */
public class RubberDuck implements Quackable {
    private final QuackObservable observable;

    public RubberDuck() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("RubberDuck.quack");
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
