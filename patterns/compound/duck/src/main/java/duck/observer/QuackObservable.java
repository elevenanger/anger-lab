package duck.observer;


/**
 * author : anger
 * date : 2022/8/16 12:06
 * description : 观察者模式
 * 被观察对象接口
 */
public interface QuackObservable {
    void registerObserver(Observer observer);
    void notifyObserver();
}
