package monitor.subject;

import monitor.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anger
 * created on 2022/7/26
 * 天气检测应用基本的类
 * 存储天气相关的基础数据
 * 一旦数据发生变动
 * 相关的展示场景需要随着数据的更新而变化
 *
 * 观察者模式 = 发布者 + 订阅者
 * 发布者称为 subject
 * 订阅者称为 observer
 *
 * WeatherData 是观察者模式中的 subject 对象
 * subject 对象管理着最重要的数据
 * 当 subject 的数据发生变化， observer 会收到通知
 * 在数据变化时，新的数据值会以某种形式的通讯传递给 observer
 * observer 订阅(注册) subject 以接收数据的更新
 *
 * 观察者模式：
 * 定义一种一对多的对象之间的依赖关系
 * 一旦一个对象状态发生变化
 * 它的所有依赖者都会被通知并且自动更新
 * 观察者模式大多围绕着包含 subject 和 observer 的类的接口进行设计
 *
 * 观察者模式的结构：
 * 1、Subject 接口：
 *    每个 subject 都可以有多个 observer
 *    对象使用这个接口注册成为 observer 或者注销 observer 关系以及通知 observer
 *    registObserver()
 *    removeObserver()
 *    notifyObserver()
 * 2、Observer 接口：
 *    每个潜在的 observer 都需要实现 Observer 接口
 *    Observer 接口只有一个抽象方法 update()
 *    当 subject 状态发生变化时调用此接口
 * 3、Subject 实体类：
 *    实现 Subject 接口
 *    除了 register() 和 remove() 方法
 *    Subject 实体实现 notify() 方法当状态发生变化时更新所有现有的 Observer
 * 4、Observer 实体类：
 *    Observer 实体类可以是实现了 Observer 接口的任意类
 *    每个 observer 都订阅了一个 Subject 实体类以接收变更
 *
 * subject 是数据的唯一所有者
 * observer 依赖主体发生变化时对其自身进行更新
 * 相较于允许多个对象控制同一份数据
 * 这是更为简洁的面向对象的设计
 *
 * 松耦合的设计：
 * 当两个对象是松耦合时
 * 它们之间可以互相交互
 * 但是互相之间所知甚少
 *
 * 观察者模式是松耦合的设计：
 * 1、subject 仅知道 observer 实现了具体的接口（Observer 接口）
 *    不需要知道 observer 具体的实现类或者任何关于 observer 的其它信息
 * 2、可以随时新增 observer
 *    因为 subject 唯一的依赖就是实现了 Observer 接口对象列表
 *    同理，可以随时移除任意 observer
 * 3、添加新的 observer ，不需要改动 subject：
 *    新的实体类需要实现 Observer 接口
 *    不需要对 subject 进行任何修改来适应新的 observer 类型
 *    新的 observer 只需要实现 Observer 接口并且注册为 observer 即可
 *    subject 不关心具体的 observer
 *    它会将通知信息发送给所有实现 Observer 接口的对象
 * 4、可以单独复用 subject 或者 observer 对象：
 *    如果有其他的场景需要用到 subject 或者 observer 对象
 *    可以简单进行复用因为它们之间的关系是松耦合的
 * 5、改变 subject 或者 observer 不会互相影响
 * 设计原则：努力实现互相交互对象之间的松耦合设计
 */
public class WeatherData implements Subject{
    /*
    Observer 对象列表
    当前 subject 实体的 observer 实体列表
     */
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        // 初始化 observes 对象
        observers = new ArrayList<>();
    }

    public float getTemperature() {
        return temperature;
    }

    private void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    private void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    private void setPressure(float pressure) {
        this.pressure = pressure;
    }

    /**
     * 采集并设置测量数据
     * 并发送通知
     * @param temperature 温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    public void setMeasurements(float temperature,
                                float humidity,
                                float pressure) {
        setTemperature(temperature);
        setHumidity(humidity);
        setPressure(pressure);
        measurementsChanged();
    }

    /*
    测量数据更新时调用的逻辑
    任意一个天气基础数据变化时需要调用此方法
    需要实现三个展示场景
    1、展示统计数据
    2、展示当前数据
    3、展示预报数据
    这些展示信息需要随着测量数据的更新而变化
    软件是不断变化的
    未来会有更多的展示场景
    需要创建一个展示场景的拓展市场可以定义新展示场景
     */
    public void measurementsChanged() {
        notifyObserver();
    }

    /**
     * 新增注册一个 observer
     * 将其添加到 observers 中
     * @param observer 新增的 observer
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 移除一个 observer
     * 从 observers 列表中删除
     * @param observer 需要移除的 observer
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 状态变化时
     * 通知所有 observer 更新状态信息
     */
    @Override
    public void notifyObserver() {
        observers.forEach(
            observer -> observer.update(temperature,
                                        humidity,
                                        pressure));
    }
}
