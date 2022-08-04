package main.commands;

/**
 * @author Anger
 * created on 2022/8/4
 * 无命令的命令
 * 什么都不做
 * 相当于一个占位符(空对象)
 * 避免空判断
 */
public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("do nothing");
    }

    @Override
    public void undo() {
        System.out.println("do nothing");
    }
}
