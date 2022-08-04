package main.commands;

import main.receivers.GarageDoor;

/**
 * @author Anger
 * created on 2022/8/4
 */
public class GarageDoorCloseCommand implements Command{
    private final GarageDoor garageDoor;

    public GarageDoorCloseCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        System.out.println("GarageDoorCloseCommand.execute");
        garageDoor.closeDoor();
    }

    @Override
    public void undo() {
        System.out.println("GarageDoorCloseCommand.undo");
        garageDoor.openDoor();
    }
}
