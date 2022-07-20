package intro;

import intro.behaviors.FlyBehavior;
import intro.behaviors.QuackBehavior;

/**
 * @author Anger
 * created on 2022/7/20
 *
 * 所有 Duck 类的超类 抽象类
 * 设计的原则：
 * 1、识别应用中变化的部分并将它与不变的部分区分开来
 * 2、编程为接口，而不是实现
 */
public abstract class Duck {

    /*
    将变化的部分分离成接口
    将接口类型添加为父类中的属性
    每一个具体的 Duck 类型对象都会为这些属性在运行时进行赋值
    赋予具体的行为
    与其将这些行为在 Duck 中实现
    Duck 将这些行为委派给具体实现类对象
    在这里不关心具体的实际的 Duck 类型
    只关心它知道如何进行 Fly 的行为
     */
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    /*
    鸭子都会游泳以及可以静止展示
    这部分行为是不变的，所以在超类中进行定义
    但是一些其他的行为
    比如飞行和鸭叫
    不是所有的鸭子都具备这个行为
    需要将这部分的行为分离出来
     */
    public void swim() {
        System.out.printf("%s swimming %n", getClass().getSimpleName());
    }

    public void display() {
        System.out.printf("%s displaying %n", getClass().getSimpleName());
    }

    /*
    将行为委派给具体的 FlyBehavior 实现类对象
     */
    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }
}
