package state;

import java.io.Serializable;

/**
 * author : anger
 * date : 2022/8/12 10:52
 * description : 售罄状态
 */
public class SoldOutState implements State , Serializable {

    private static final long serialVersionUID = 2L;
    private final transient GumballMachine machine;

    public SoldOutState(GumballMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("口香糖已售罄，无法投币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("没有硬币");
    }

    @Override
    public void turnCrank() {
        System.out.println("已售罄，请勿摇杆");
    }

    @Override
    public void dispense() {
        System.out.println("没有分配口香糖");
    }

    @Override
    public void refill() {
        System.out.println("SoldOutState.refill");
        machine.setState(machine.getNoQuarterState());
    }
}
