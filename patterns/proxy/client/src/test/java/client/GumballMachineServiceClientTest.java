package client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import state.remote.GumballMachineRemote;

import java.rmi.Naming;
/**
 * author : anger
 * date : 2022/8/14 12:41
 * description : GumballMachine 远程调用客户端测试
 */
class GumballMachineServiceClientTest {

    @Test
    void machineServiceTest() {
        try {
            GumballMachineRemote machine =
                (GumballMachineRemote) Naming.lookup("rmi://127.0.0.1:1099/gumball");

            Assertions.assertNotNull(machine);

            GumballMachineServiceClient client =
                new GumballMachineServiceClient(machine);

            client.report();
            client.insertQuarter();
            client.turnCrank();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}