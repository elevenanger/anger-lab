package monitor.observer;

import monitor.display.ElementDisplay;
import monitor.subject.WeatherData;

/**
 * @author Anger
 * created on 2022/7/27
 * 展示当前状态
 */
public class CurrentConditionDisplay implements Observer, ElementDisplay {
    private float temperature;
    private float humidity;
    /*
    在 Observer 对象中保存一份对 Subject 的引用
    一旦 Observer 自身需要移除对 subject 的观察关系
    可以很方便进行调用 remove() 方法
     */
    private final WeatherData weatherData;

    /**
     * 将需要注册的 Subject 对象作为构造函数的参数
     * 在对象构造时调用 Subject 的注册方法
     * 对象实例化即完成注册
     * @param weatherData 需要注册的 Observer 对象 */
    public CurrentConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    /**
     * 展示方法
     * 实现具体展示逻辑
     */
    @Override
    public void display() {
        System.out.printf("当前天气信息：温度：%.2f 湿度：%.2f%n",
                temperature,
                humidity);
    }

    /**
     * 更新方法
     * 获取 subject 对象状态变化
     * 每次更新操作都调用 display() 方法
     * 更新展示信息
     */
    @Override
    public void update() {
        this.humidity = weatherData.getHumidity();
        this.temperature = weatherData.getTemperature();
        display();
    }
}
