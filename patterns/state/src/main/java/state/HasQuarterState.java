package state;

/**
 * author : liuanglin
 * date : 2022/8/12 10:53
 * description : 已投币状态
 */
public class HasQuarterState implements State {

    private final GumballMachine machine;

    public HasQuarterState(GumballMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已投币，请勿重复投币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("币已经退回，请注意查看");
        machine.setState(machine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("口香糖已出仓");
        machine.setState(machine.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println("还没有分配口香糖");
    }

    @Override
    public void refill() {
        System.out.println("不需要加口香糖");
    }
}
