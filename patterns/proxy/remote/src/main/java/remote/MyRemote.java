package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * author : liuanglin
 * date : 2022/8/13 21:21
 * description : 远程服务接口
 * 定义可以被远程调用的对象类型和方法
 * 继承 RMI Remote 接口
 */
public interface MyRemote extends Remote {
    /**
     * 远程调用方法
     * @return String 返回值类型需要实现 Serializable
     * @throws RemoteException 每个远程方法都是有风险的，抛出异常
     */
    String sayHello() throws RemoteException;
}
