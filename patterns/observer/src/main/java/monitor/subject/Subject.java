package monitor.subject;

import monitor.observer.Observer;

/**
 * @author Anger
 * created on 2022/7/27
 * 观察者模式中的 Subject 接口
 */
public interface Subject {
    /*
    Subject 接口中定义注册、移除和通知观察者的方法
    注册和移除方法需要接收一个 Observer 对象作为参数
     */
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    /*
    当 Subject 对象发生变化时
    通知观察者
     */
    void notifyObserver();
}
