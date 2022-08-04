package main.commands;

import main.receivers.GarageDoor;

/**
 * @author Anger
 * created on 2022/8/3
 */
public class GarageDoorOpenCommand implements Command {
    private final GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        System.out.println("GarageDoorOpenCommand.execute");
        garageDoor.openDoor();
    }

    @Override
    public void undo() {
        System.out.println("GarageDoorOpenCommand.undo");
        garageDoor.closeDoor();
    }
}
