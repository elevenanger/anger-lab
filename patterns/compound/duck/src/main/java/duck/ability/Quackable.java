package duck.ability;

import duck.observer.QuackObservable;

/**
 * author : liuanglin
 * date : 2022/8/16 08:24
 * description : 定义鸭子叫的行为接口
 */
public interface Quackable extends QuackObservable {
    void quack();
}
