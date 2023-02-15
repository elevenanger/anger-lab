package duck.factory;

import duck.ability.Quackable;

/**
 * author : anger
 * date : 2022/8/16 09:52
 * description : Duck 抽象工厂
 */
public abstract class AbstractDuckFactory {
    public abstract Quackable createMallardDuck();
    public abstract Quackable createRedheadDuck();
    public abstract Quackable createDuckCall();
    public abstract Quackable createRubberDuck();
}
