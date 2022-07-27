package monitor.observer;

/**
 * @author Anger
 * created on 2022/7/27
 * 观察者模式中的 Observer 接口
 */
public interface Observer {
    /*
    update() 方法
    一旦 subject 的状态发生变化则调用 Observer 的此方法
    参数为 Observer 需要从 Subject 获取的状态值
     */
    void update(float temp, float humidity, float pressure);
}
