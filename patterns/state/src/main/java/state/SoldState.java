package state;

/**
 * author : liuanglin
 * date : 2022/8/12 10:51
 * description : 可售状态
 */
public class SoldState implements State {
    private final GumballMachine machine;

    public SoldState(GumballMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已经给过你口香糖");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("你已经摇过摇杆了");
    }

    @Override
    public void turnCrank() {
        System.out.println("不要重复摇杆");
    }

    @Override
    public void dispense() {
        machine.releaseBall();
        if (machine.getCount() > 0)
            machine.setState(machine.getNoQuarterState());
        else {
            System.out.println("口香糖球已经售罄");
            machine.setState(machine.getSoldOutState());
        }
    }
    @Override
    public void refill() {
        System.out.println("不需要加口香糖");
    }
}
