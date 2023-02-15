package duck.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * author : anger
 * date : 2022/8/16 12:13
 */
public class Observable implements QuackObservable {
    private final List<Observer> observers = new ArrayList<>();
    private final QuackObservable duck;

    public Observable(QuackObservable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver() {
        observers.forEach(
            observer -> observer.update(duck));
    }
}
