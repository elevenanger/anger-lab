package main.client;

import main.commands.GarageDoorCloseCommand;
import main.commands.GarageDoorOpenCommand;
import main.commands.LightOffCommand;
import main.commands.LightOnCommand;
import main.invoker.RemoteControl;
import main.receivers.GarageDoor;
import main.receivers.Light;

/**
 * @author Anger
 * created on 2022/8/4
 * 客户端
 */
public class RemoteLoader {

    public static void main(String[] args) {
        // 创建 invoker 对象
        RemoteControl control = new RemoteControl();
        System.out.println(control);

        // 创建 receiver 对象
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();

        // 设置命令对象
        control.setCommand(0,
                new LightOnCommand(light),
                new LightOffCommand(light));
        control.setCommand(1,
                new GarageDoorOpenCommand(garageDoor),
                new GarageDoorCloseCommand(garageDoor));

        System.out.println(control);

        // 执行命令操作
        control.onButtonWasPushed(0);
        System.out.println(control);
        control.undoButtonWasPushed();
        System.out.println(control);
        control.offButtonWasPushed(0);
        System.out.println(control);
        control.undoButtonWasPushed();
        System.out.println(control);
        control.onButtonWasPushed(1);
        System.out.println(control);
        control.undoButtonWasPushed();
        System.out.println(control);
        control.offButtonWasPushed(1);
        System.out.println(control);
        control.undoButtonWasPushed();
        System.out.println(control);
    }
}
