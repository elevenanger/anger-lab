package state;

/**
 * author : liuanglin
 * date : 2022/8/12 10:48
 * description : 状态模式
 * 定义状态接口
 * 所有具体的状态都需要实现该接口
 * 接口中的方法为会发生在口香糖机器的操作
 */
public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
    void refill();
}
