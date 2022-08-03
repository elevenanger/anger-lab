package main.commands;

/**
 * @author Anger
 * created on 2022/8/3
 * 命令模式：
 * 将请求封装为一个对象，可以参数化不同的请求
 * 命令模式将操作的请求者与实际执行操作的对象分离
 * 1、客户端（client）：
 *    负责创建 Command 对象
 *    Command 对象包括一组操作和执行操作的接收者
 * 2、Command 对象：
 *    Command 提供一个 execute() 方法
 *    封装了具体的操作
 *    这个方法可以被调用以调用接收者执行该操作
 * 3、Invoker(调用者)：
 *    客户端调用调用者的 setCommand() 方法
 *    将 Command 对象存储在 Invoker 对象中
 *    客户端告诉 Invoker 在合适的时机调用 Command 对象的 execute() 方法
 * 4、接收者（Receiver）：
 *    Invoker 调用 Command 对象的 execute() 方法
 *    导致 execute() 方法中封装的操作被 Receiver 执行
 *
 */
public interface Command {

    void execute();

}
