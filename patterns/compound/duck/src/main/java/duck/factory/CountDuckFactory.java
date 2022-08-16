package duck.factory;

import duck.ability.Quackable;
import duck.decorator.QuackCounter;
import duck.entity.DuckCall;
import duck.entity.MallardDuck;
import duck.entity.RedHeadDuck;
import duck.entity.RubberDuck;

/**
 * author : liuanglin
 * date : 2022/8/16 09:56
 * description : Duck 计数工厂
 */
public class CountDuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }

    @Override
    public Quackable createRedheadDuck() {
        return new QuackCounter(new RedHeadDuck());
    }

    @Override
    public Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }

    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
