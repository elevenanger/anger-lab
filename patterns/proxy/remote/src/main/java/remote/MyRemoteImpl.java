package remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * author : liuanglin
 * date : 2022/8/13 21:28
 * description : MyRemote 接口实现类
 * 远程方法的具体实现
 * 继承 UnicastRemoteObject 提供暴露为远程对象的能力
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    /**
     * 定义一个匹配超类的无参构造函数
     * @throws RemoteException 抛出 RemoteException
     */
    protected MyRemoteImpl() throws RemoteException {}

    /**
     * 接口方法的具体实现
     * @return 可序列化的对象或者基元类型
     * @throws RemoteException 抛出 RemoteException
     */
    @Override
    public String sayHello() throws RemoteException {
        System.out.println("MyRemoteImpl.sayHello");
        return "Server Says : Hello.";
    }

    public static void main(String[] args) {
        /*
        定义好远程服务
        需要使得它能够被客户端调用
        实例化服务对象
        将其注册进 RMI 注册中心
        注册了实现类对象
        RMI 系统会将存根放在注册中心中
        这个存根正是客户端所需要的
        使用 Naming.rebind(name, service) 方法注册服务
        name 是服务名
        客户端根据服务名在注册中心查找所需要的服务
        一旦绑定了服务对象
        RMI 封装服务对象作为存根并将其存放在注册中心
            LocateRegistry.createRegistry(1099) 创建指定端口的注册中心
         */
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println(registry);
            MyRemote service = new MyRemoteImpl();
            Naming.rebind("rmi://127.0.0.1:1099/remoteHello", service);
            System.out.println("Remote Server Started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
