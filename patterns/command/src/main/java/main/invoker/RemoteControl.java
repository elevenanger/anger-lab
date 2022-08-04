package main.invoker;

import main.commands.Command;
import main.commands.NoCommand;

/**
 * @author Anger
 * created on 2022/8/4
 * 遥控器类
 * invoker 类
 */
public class RemoteControl {
    private final int slotNumber = 7;
    /*
    持有开关命令的对象数组
     */
    Command[] onCommands;
    Command[] offCommands;
    // 取消命令，记录最后一个操作的命令对象
    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[slotNumber];
        offCommands = new Command[slotNumber];

        /*
        初始化遥控器数组
        将所有的插槽都设置为无命令
         */
        Command nCommand = new NoCommand();
        for (int i = 0; i < slotNumber; i++) {
            onCommands[i] = nCommand;
            offCommands[i] = nCommand;
        }
        undoCommand = nCommand;
    }

    /**
     * 设置插槽的 Command 对象
     * @param slot 插槽编号
     * @param onCommand 开启命令
     * @param offCommand 关闭命令
     */
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    /**
     * 按下指定插槽的 on 按钮，
     * 调用 onCommand.execute() 唤醒 receiver 执行相应操作
     * @param slot 插槽编号
     */
    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        // 执行任何操作都将 undoCommand 赋值为此命令对象
        undoCommand = onCommands[slot];
    }

    /**
     * 按下指定插槽的 off 按钮
     * 调用对应的 offCommand.execute() 方法
     * @param slot 插槽编号
     */
    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    /**
     * 执行 undo 操作
     */
    public void undoButtonWasPushed() {
        System.out.println("RemoteControl.undoButtonWasPushed");
        undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("\n----Remote Control----\n");
        for (int i = 0; i < onCommands.length; i++) {
            buffer.append("[slot ").append(i).append("] ")
                    .append(onCommands[i].getClass().getSimpleName())
                    .append("   ")
                    .append(offCommands[i].getClass().getSimpleName())
                    .append("\n");
        }
        buffer.append("[undo]")
                .append("   ")
                .append(undoCommand.getClass().getSimpleName())
                .append("\n");
        return buffer.toString();
    }
}
