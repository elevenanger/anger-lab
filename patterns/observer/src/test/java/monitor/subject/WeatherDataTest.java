package monitor.subject;

import monitor.observer.CurrentConditionDisplay;
import monitor.observer.ForecastDisplay;
import monitor.observer.StatisticsDisplay;
import monitor.observer.ThirdPartyDisplay;
import org.junit.jupiter.api.Test;

/**
 * @author Anger
 * created on 2022/7/27
 */
class WeatherDataTest {

    @Test
    void testWeatherDataNotice() {
        WeatherData weatherData = new WeatherData();

        CurrentConditionDisplay currentConditionDisplay =
        new CurrentConditionDisplay(weatherData);

        ForecastDisplay forecastDisplay =
            new ForecastDisplay(weatherData);

        StatisticsDisplay statisticsDisplay =
            new StatisticsDisplay(weatherData);

        ThirdPartyDisplay thirdPartyDisplay =
            new ThirdPartyDisplay(weatherData);

        weatherData.setMeasurements(70.88f, 20.5f, 100);

        weatherData.removeObserver(forecastDisplay);

        weatherData.setMeasurements(60.88f, 27.5f, 10);
    }
}