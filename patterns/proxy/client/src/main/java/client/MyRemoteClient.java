package client;

import remote.MyRemote;

import java.rmi.Naming;

/**
 * author : liuanglin
 * date : 2022/8/13 22:09
 * description : RMI 服务客户端
 */
public class MyRemoteClient {
    /**
     * RMI 客户端方法
     * Naming.lookup() 从注册中心查找服务
     * 获取服务实例
     * 转换成服务对象
     * 即可调用服务方法
     */
    public void go() {
        try {
            MyRemote service =
                (MyRemote) Naming.lookup(
                    "rmi://127.0.0.1:1099/remoteHello");
            System.out.println(service.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
