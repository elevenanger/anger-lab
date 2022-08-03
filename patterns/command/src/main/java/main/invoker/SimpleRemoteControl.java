package main.invoker;

import main.commands.Command;

/**
 * @author Anger
 * created on 2022/8/3
 * invoker 类
 * 保存 Command 对象
 * 执行 Command 对象的 execute() 方法
 */
public class SimpleRemoteControl {

    /*
    实例域变量引用 Command 对象
     */
    Command slot;

    /**
     * Command setter 方法
     * 这个方法可以反复调用
     * 客户端可以改变 Invoker 实例域引用的 Command 对象
     * @param slot Command 对象
     */
    public void setSlot(final Command slot) {
        this.slot = slot;
    }

    /**
     * 调用 Invoker 的这个方法
     * 执行 Command.execute() 方法
     */
    public void pressButton() {
        System.out.println("SimpleRemoteControl.pressButton");
        slot.execute();
    }
}
