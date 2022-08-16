package duck.adapter;

import duck.ability.Quackable;
import duck.entity.Goose;
import duck.observer.Observable;
import duck.observer.Observer;
import duck.observer.QuackObservable;

/**
 * author : liuanglin
 * date : 2022/8/16 08:53
 * description : goose 适配器
 */
public class GooseAdapter implements Quackable {
    /*
    使用适配器模式
    GooseAdapter 实现 Quackable 接口
    表现鸭子的行为
     */
    private final Goose goose;
    private final QuackObservable observable;
    public GooseAdapter(Goose goose) {
        this.goose = goose;
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        goose.honk();
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
