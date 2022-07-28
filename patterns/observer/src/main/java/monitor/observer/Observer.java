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
    参数为 Observer 需要从 Subject 接收的状态值
    Subject push 数据到 observer

    不设置任何参数
    Observer 收到状态更新的通知后自己选择需要从 subject 拉取的数据主动拉取
     */
    void update();
}
