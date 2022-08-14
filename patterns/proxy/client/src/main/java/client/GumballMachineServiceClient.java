package client;

import state.remote.GumballMachineRemote;

import java.rmi.RemoteException;

/**
 * author : liuanglin
 * date : 2022/8/14 12:21
 * description : 远程调用 GumballMachine 接口客户端应用
 */
public class GumballMachineServiceClient {
    private final GumballMachineRemote gumballMachine;

    public GumballMachineServiceClient(GumballMachineRemote gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void report() {
        System.out.println("GumballMachineServiceClient.report");
        try {
            System.out.printf("Machine Location:%s%n", gumballMachine.getLocation());
            System.out.printf("Machine Status:%s%n", gumballMachine.getState());
            System.out.printf("Machine Inventory:%d%n", gumballMachine.getCount());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertQuarter() {
        System.out.println("GumballMachineServiceClient.insertQuarter");
        try {
            gumballMachine.insertQuarter();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void turnCrank() {
        System.out.println("GumballMachineServiceClient.turnCrank");
        try {
            gumballMachine.turnCrank();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
