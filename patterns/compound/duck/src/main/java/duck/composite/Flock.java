package duck.composite;

import duck.ability.Quackable;
import duck.observer.Observer;
import duck.observer.QuackObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/8/16 10:20
 * description : 一群鸭子
 */
public class Flock implements Quackable {

    private final List<Quackable> quackers = new ArrayList<>();

    public void add(Quackable quackable) {
        quackers.add(quackable);
    }

    @Override
    public void quack() {
        quackers.forEach(Quackable::quack);
    }

    @Override
    public void registerObserver(Observer observer) {
        quackers.forEach(quackable -> quackable.registerObserver(observer));
    }

    @Override
    public void notifyObserver() {
        quackers.forEach(QuackObservable::notifyObserver);
    }
}
