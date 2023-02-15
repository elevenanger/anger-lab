package adapter;

/**
 * author : anger
 * date : 2022/8/5 20:59
 * description : Duck 实现类
 */
public class MallardDuck implements Duck {
    @Override
    public void fly() {
        System.out.println("MallardDuck.fly");
    }

    @Override
    public void quack() {
        System.out.println("MallardDuck.quack");
    }
}
