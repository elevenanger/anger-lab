package duck.entity;

import duck.ability.Quackable;
import duck.observer.Observable;
import duck.observer.Observer;
import duck.observer.QuackObservable;

/**
 * author : liuanglin
 * date : 2022/8/16 08:27
 * description : 鸭叫声
 */
public class DuckCall implements Quackable {
    private final QuackObservable observable;

    public DuckCall() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("呱呱呱");
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
