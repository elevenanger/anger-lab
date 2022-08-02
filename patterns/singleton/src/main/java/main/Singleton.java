package main;

/**
 * @author Anger
 * created on 2022/8/2
 *
 * 单例设计模式
 * 确保一个类只有一个对象实例
 * 并且提供对于该对象实例的访问点
 */
public class Singleton{

    /*
    单例设计模式中含有一个静态变量
    引用唯一一个单例类实例
    它是一个 static 变量
    使用 volatile 确保该变量在所有线程之间的可见性
     */
    private static volatile Singleton uniqueInstance;

    /*
    声明构造函数为 private
    只有单例静态方法可以实例化该类对象
     */
    private Singleton() {}

    /**
     * 实例化 Singleton 对象
     * @return Singleton 对象实例
     */
    public static Singleton getInstance() {
        /*
        如果唯一的实例变量引用的对象为空
        则说明还没有实例化过该对象
         */
        if (uniqueInstance == null)
            /*
            通过 private 构造函数实例化对象
            将其赋值给 static 变量 uniqueInstance
            如果不需要该实例
            则这个方法永远不会被调用
            对象不会被创建
            这是一个延迟创建对象的操作
            synchronized 声明对象的创建
            防止多线程导致对象重复创建
             */
            synchronized (Singleton.class) {
                if (uniqueInstance == null)
                    uniqueInstance = new Singleton();
            }
        /*
        如果 static 变量指向的唯一实例不为空
        则说明该对象已经被创建
        直接返回已创建的对象实例
         */
        return uniqueInstance;
    }

}
