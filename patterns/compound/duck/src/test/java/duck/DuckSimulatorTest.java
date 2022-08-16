package duck;

import duck.ability.Quackable;
import duck.adapter.GooseAdapter;
import duck.composite.Flock;
import duck.entity.Goose;
import duck.factory.AbstractDuckFactory;
import duck.factory.CountDuckFactory;
import duck.observer.Observer;
import duck.observer.QuackObserver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static duck.decorator.QuackCounter.getQuackCount;

/**
 * author : liuanglin
 * date : 2022/8/16 08:49
 */
class DuckTest {
    static Observer observer = new QuackObserver();
    static Consumer<Quackable> simulator = quackable -> {
        quackable.registerObserver(observer);
        quackable.quack();};
    AbstractDuckFactory factory = new CountDuckFactory();


    @Test
    void abstractFactoryTest() {
        List<Quackable> ducks =
            Arrays.asList(
                factory.createRubberDuck(),
                factory.createDuckCall(),
                factory.createRedheadDuck(),
                factory.createMallardDuck(),
                new GooseAdapter(new Goose())
            );
        ducks.forEach(simulator);
        System.out.printf("鸭子数量：%d%n", getQuackCount());
    }

    @Test
    void compositeTest() {
        Flock flockOfMallards = new Flock();
        flockOfMallards.add(factory.createMallardDuck());
        flockOfMallards.add(factory.createMallardDuck());
        flockOfMallards.add(factory.createMallardDuck());

        Flock flockOFRubberDucks = new Flock();
        flockOFRubberDucks.add(factory.createRubberDuck());
        flockOFRubberDucks.add(factory.createRubberDuck());
        flockOFRubberDucks.add(factory.createRubberDuck());

        Flock flockOfRedheadDucks = new Flock();
        flockOfRedheadDucks.add(factory.createRedheadDuck());
        flockOfRedheadDucks.add(factory.createRedheadDuck());
        flockOfRedheadDucks.add(factory.createRedheadDuck());

        Flock flockOfDuckCalls = new Flock();
        flockOfDuckCalls.add(factory.createDuckCall());
        flockOfDuckCalls.add(factory.createDuckCall());
        flockOfDuckCalls.add(factory.createDuckCall());

        Flock flockOfDucks = new Flock();
        flockOfDucks.add(flockOfMallards);
        flockOfDucks.add(flockOFRubberDucks);
        flockOfDucks.add(flockOfDuckCalls);
        flockOfDucks.add(flockOfRedheadDucks);

        simulator.accept(flockOfDucks);
        System.out.printf("鸭子数量：%d%n", getQuackCount());
    }

}