package main.receivers;

/**
 * @author Anger
 * created on 2022/8/3
 * 接收者
 * 执行具体操作的对象
 */
public class Light {

    private boolean lightOn;

    public void setLightOn(boolean lightOn) {
        this.lightOn = lightOn;
    }

    public void on() {
        System.out.println("Light.on");
        setLightOn(true);
    }

    public void off() {
        System.out.println("Light.off");
        setLightOn(false);
    }
}
