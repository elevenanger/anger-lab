package main.receivers;

/**
 * @author Anger
 * created on 2022/8/3
 * 接收者
 * 执行具体操作的对象
 * 多个 receiver 之间不需要符合同样的约束（接口）
 * receiver 之间互相独立
 * 由 Command 对象负责调用 receiver  的方法，执行具体的操作
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
