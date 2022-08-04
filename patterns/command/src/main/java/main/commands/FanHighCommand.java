package main.commands;

import main.receivers.Fan;

import static main.receivers.Fan.*;

/**
 * @author Anger
 * created on 2022/8/4
 */
public class FanHighCommand implements Command {
    private final Fan fan;
    private Speed prevSpeed;

    public FanHighCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        System.out.println("FanHighCommand.execute");
        prevSpeed = fan.getSpeed();
        System.out.println("prevSpeed " + prevSpeed);
        fan.high();
    }

    @Override
    public void undo() {
        System.out.println("FanHighCommand.undo");
        switch (prevSpeed) {
            case LOW:fan.low();
                break;
            case HIGH:fan.high();
                break;
            case OFF:fan.off();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + prevSpeed);
        }
    }
}
