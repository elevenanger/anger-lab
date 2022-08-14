package remote.gumball;

import state.GumballMachine;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * author : liuanglin
 * date : 2022/8/14 12:02
 * description : GumballMachine 远程服务
 */
public class GumballMachineRemoteService {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println(registry);
            Remote gumballMachineService =
                new GumballMachine("Dong Guan", 100);
            Naming.rebind("rmi://127.0.0.1:1099/gumball", gumballMachineService);
            System.out.printf("Gumball Machine:%n%s%n Started!",gumballMachineService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
