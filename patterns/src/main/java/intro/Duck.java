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
 * 3、偏好组合胜于继承
 *
 * 封装行为：
 * 不再将鸭子的行为视为一组行为
 * 而是将其视为一组算法
 * 算法表达的鸭子会做的事情
 *
 * 将两个或者以上的类放在一起的行为叫做组合
 * 相较于直接继承行为 (is-a 关系)
 * 鸭子通过组合合适的对象获取正确的行为 （ has-a 关系）
 *
 * 这种设计模式称之为 ：策略模式
 * 定义并封装一系列的算法族
 * 使之可以互相替换
 * 策略模式使得算法可以独立于使用它的客户 (算法：behavior 客户：duck)
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

    /*
    setter 方法可以动态地改变对象的行为
     */
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
