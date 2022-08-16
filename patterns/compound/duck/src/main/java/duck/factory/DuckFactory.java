package duck.factory;

import duck.ability.Quackable;
import duck.entity.DuckCall;
import duck.entity.MallardDuck;
import duck.entity.RedHeadDuck;
import duck.entity.RubberDuck;

/**
 * author : liuanglin
 * date : 2022/8/16 09:55
 * description : Duck 工厂
 */
public class DuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    public Quackable createRedheadDuck() {
        return new RedHeadDuck();
    }

    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
