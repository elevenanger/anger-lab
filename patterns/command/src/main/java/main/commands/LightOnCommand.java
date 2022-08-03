package main.commands;

import main.receivers.Light;

/**
 * @author Anger
 * created on 2022/8/3
 * 具体的 Command 对象
 * 实现 Command 接口
 */
public class LightOnCommand implements Command {

    private final Light light;

    /**
     * 将需要 Command 对象控制的对象作为构造函数的参数
     * 这个对象就是执行最终操作的 receiver
     * @param light 被控制的对象-receiver
     */
    public LightOnCommand(Light light) {
        this.light = light;
    }

    /**
     * execute() 方法调用 Receiver 对象的具体操作方法
     */
    @Override
    public void execute() {
        System.out.println("LightOnCommand.execute");
        light.on();
    }
}
