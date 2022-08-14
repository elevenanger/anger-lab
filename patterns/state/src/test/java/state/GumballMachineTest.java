package state;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/8/12 20:37
 * description : 状态模式测试
 */
@Slf4j
class GumballMachineTest {

    @Test
    void testState() throws RemoteException {
        GumballMachine gumballMachine = new GumballMachine("Chang An",10);
        log.info(gumballMachine.toString());

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        log.info(gumballMachine.toString());

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        log.info(gumballMachine.toString());
    }

}