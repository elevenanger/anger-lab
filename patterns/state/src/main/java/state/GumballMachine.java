package state;

/**
 * author : liuanglin
 * date : 2022/8/12 12:07
 * description : 机器
 * 状态模式：
 * 允许对象在其内部状态发生变化时发生改变其行为,对象的类将会发生变化（状态）
 * 状态模式将每个状态封装到独立的类并委托给代表当前状态的类
 * 状态模式的组成：
 * 1、上下文：
 *    上下文是拥有一系列内部状态的类
 *    GumballMachine 就是一个上下文
 *    上下文定义 request() 方法
 *    无论何时调用 request() 方法
 *    都会委托给状态对象进行处理
 * 2、状态接口：
 *    状态定义所有状态的通用方法
 *    所有的状态类都实现状态接口
 *    它们之间是可以互相进行替换的
 * 3、具体的状态类：
 *    具体的状态类对象处理上下文中的请求
 *    每个具体的状态都为每一个 request() 方法提供一个实现
 *    通过这种方式，上下文切换状态它的行为也会随之变化
 */
public class GumballMachine {
    private final State soldOutState;
    private final State noQuarterState;
    private final State hasQuarterState;
    private final State soldState;
    private final State winnerState;
    private State state;
    int count = 0;

    public GumballMachine(int numberOfGumballs) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);

        if (numberOfGumballs > 0){
            state = noQuarterState;
            count = numberOfGumballs;
        }
        else
            state = soldOutState;
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public State getState() {
        return state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public int getCount() {
        return count;
    }

    public void releaseBall() {
        System.out.println("从槽道将口香糖球传出");
        if (count > 0)
            count = count - 1;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void refill(int gumballNumber) {
        if (gumballNumber <= 0) throw new IllegalArgumentException();
        count += gumballNumber;
        state.refill();
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
            "state=" + state.getClass().getSimpleName() +
            ", count=" + count +
            '}';
    }
}
