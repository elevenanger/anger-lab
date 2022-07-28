package monitor.observer;

import monitor.display.ElementDisplay;
import monitor.subject.WeatherData;

/**
 * @author Anger
 * created on 2022/7/27
 */
public class ThirdPartyDisplay implements Observer, ElementDisplay {
    private float temp;
    private float humidity;
    private float pressure;
    private final WeatherData weatherData;

    public ThirdPartyDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.printf("自定义天气信息：温度：%.2f 湿度：%.2f 气压：%.2f%n",
                temp,
                humidity,
                pressure);
    }

    @Override
    public void update() {
        this.temp = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        this.pressure = weatherData.getPressure();
        display();
    }
}
