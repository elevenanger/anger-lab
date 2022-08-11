package main.invoker;

import main.commands.Command;
import main.commands.GarageDoorOpenCommand;
import main.commands.LightOnCommand;
import main.receivers.GarageDoor;
import main.receivers.Light;
import org.junit.jupiter.api.Test;

/**
 * @author Anger
 * created on 2022/8/3
 */
class SimpleRemoteControlTest {

    /**
     * 在这里，这个测试方法充当了命令模式中的 client 角色
     * client 负责创建具体的 Command 对象
     * 设置 receiver
     */
    @Test
    void commandTest() {
        // receiver 对象，执行操作请求
        Light light = new Light();
        // command 对象，传递 receiver 对象作为构造函数的参数
        Command command = new LightOnCommand(light);
        // 创建 Invoker 对象
        SimpleRemoteControl control = new SimpleRemoteControl();
        // 将 command 对象赋值给 invoker 实例域
        control.setSlot(command);
        // invoker 方法中调用 command.execute() 方法
        control.pressButton();

        GarageDoor garageDoor = new GarageDoor();

        command = new GarageDoorOpenCommand(garageDoor);
        control.setSlot(command);
        control.pressButton();

        control.pressButton();
    }
}