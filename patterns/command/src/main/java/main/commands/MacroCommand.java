package main.commands;

import java.util.Arrays;

/**
 * @author Anger
 * created on 2022/8/4
 * 宏命令
 */
public class MacroCommand implements Command {
    Command [] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        Arrays.stream(commands).forEach(Command::execute);
    }

    @Override
    public void undo() {
        System.out.println("MacroCommand.undo");
    }
}
