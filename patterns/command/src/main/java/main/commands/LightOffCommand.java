package main.commands;

import main.receivers.Light;

/**
 * @author Anger
 * created on 2022/8/4
 */
public class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        System.out.println("LightOffCommand.execute");
        light.off();
    }

    @Override
    public void undo() {
        System.out.println("LightOffCommand.undo");
        light.on();
    }
}
