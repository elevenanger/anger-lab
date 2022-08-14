package state;

import java.io.Serializable;

/**
 * author : liuanglin
 * date : 2022/8/12 10:53
 * description : 未投币状态
 */
public class NoQuarterState implements State, Serializable {

    private static final long serialVersionUID = 2L;
    private final transient GumballMachine machine;

    public NoQuarterState(GumballMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已投币");
        machine.setState(machine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("还未投币，无法吐出硬币");
    }

    @Override
    public void turnCrank() {
        System.out.println("还未投币，请投币再试");
    }

    @Override
    public void dispense() {
        System.out.println("请先支付");
    }

    @Override
    public void refill() {
        System.out.println("不需要加口香糖");
    }
}
