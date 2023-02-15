package state.remote;

import state.State;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * author : anger
 * date : 2022/8/14 11:38
 * description : 实现 Remote 接口，可以注册为远程服务
 * 需要确保接口中的返回对象类型都是可序列化的
 */
public interface GumballMachineRemote extends Remote {
    /*
    接口中的方法都需要声明抛出 RemoteException 异常
     */
    int getCount() throws RemoteException;
    String getLocation() throws RemoteException;
    State getState() throws RemoteException;
    void insertQuarter() throws RemoteException;
    void ejectQuarter() throws RemoteException;
    void turnCrank() throws RemoteException;
}
