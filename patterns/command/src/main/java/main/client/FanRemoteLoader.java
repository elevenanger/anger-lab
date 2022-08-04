package main.client;

import main.commands.FanHighCommand;
import main.commands.FanLowCommand;
import main.commands.FanOffCommand;
import main.invoker.RemoteControl;
import main.receivers.Fan;

/**
 * @author Anger
 * created on 2022/8/4
 */
public class FanRemoteLoader {

    public static void main(String[] args) {
        RemoteControl control = new RemoteControl();
        System.out.println(control);

        Fan fan = new Fan();

        control.setCommand(0,
                new FanLowCommand(fan),
                new FanOffCommand(fan));

        control.setCommand(1,
                new FanHighCommand(fan),
                new FanOffCommand(fan));
        System.out.println(control);

        control.onButtonWasPushed(0);
        System.out.println(control);

        control.onButtonWasPushed(1);
        System.out.println(control);
        control.undoButtonWasPushed();
        System.out.println(control);
    }
}
