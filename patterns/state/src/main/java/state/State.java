package state;

import java.io.Serializable;

/**
 * author : liuanglin
 * date : 2022/8/12 10:48
 * description : 状态模式
 * 定义状态接口
 * 所有具体的状态都需要实现该接口
 * 接口中的方法为会发生在口香糖机器的操作
 * State 对象需要作为远程调用对象，需要支持序列化
 */
public interface State extends Serializable {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
    void refill();
}
