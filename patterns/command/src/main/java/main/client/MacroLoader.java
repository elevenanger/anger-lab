package main.client;

import main.commands.*;
import main.invoker.RemoteControl;
import main.receivers.Fan;
import main.receivers.GarageDoor;
import main.receivers.Light;

/**
 * @author Anger
 * created on 2022/8/4
 */
public class MacroLoader {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();
        Fan fan = new Fan();

        Command[] onCommands = {
                new FanLowCommand(fan),
                new FanHighCommand(fan),
                new LightOnCommand(light),
                new GarageDoorOpenCommand(garageDoor)};

        Command[] offCommands = {
                new FanOffCommand(fan),
                new FanOffCommand(fan),
                new LightOffCommand(light),
                new GarageDoorCloseCommand(garageDoor)};

        MacroCommand onMacroCommand = new MacroCommand(onCommands);
        MacroCommand offMacroCommand = new MacroCommand(offCommands);

        remoteControl.setCommand(0, onMacroCommand, offMacroCommand);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
    }
}
