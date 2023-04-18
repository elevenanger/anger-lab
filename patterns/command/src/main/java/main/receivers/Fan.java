package main.receivers;

/**
 * @author Anger
 * created on 2022/8/4
 * 三档电风扇
 */
public class Fan {
    public enum Speed {OFF, LOW, HIGH}

    private Speed speed;

    public Fan() {
        speed = Speed.OFF;
    }

    public void high() {
        System.out.println("Fan.high");
        speed = Speed.HIGH;
    }

    public void low() {
        System.out.println("Fan.low");
        speed = Speed.LOW;
    }

    public void off() {
        System.out.println("Fan.off");
        speed = Speed.OFF;
    }

    public Speed getSpeed() {
        return speed;
    }
}
