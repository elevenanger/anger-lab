package state;

/**
 * author : liuanglin
 * date : 2022/8/12 20:30
 * description : 中奖状态
 */
public class WinnerState implements State{
    private final GumballMachine machine;

    public WinnerState(GumballMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("WinnerState.insertQuarter");
        System.out.println("请勿投币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("WinnerState.ejectQuarter");
        System.out.println("不能吐出硬币");
    }

    @Override
    public void turnCrank() {
        System.out.println("WinnerState.turnCrank");
        System.out.println("请勿摇动摇杆");
    }

    @Override
    public void dispense() {
        machine.releaseBall();
        if (machine.getCount() == 0)
            machine.setState(machine.getSoldOutState());
        else {
            machine.releaseBall();
            System.out.println("恭喜你中奖了！奖励一个球！");
            if (machine.getCount() > 0)
                machine.setState(machine.getNoQuarterState());
            else {
                System.out.println("售罄");
                machine.setState(machine.getSoldOutState());
            }
        }
    }

    @Override
    public void refill() {
        System.out.println("不需要加口香糖");
    }
}
