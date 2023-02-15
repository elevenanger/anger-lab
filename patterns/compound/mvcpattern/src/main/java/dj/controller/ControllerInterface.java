package dj.controller;

/**
 * author : anger
 * date : 2022/8/17 09:36
 * controller 定义所有 view 可以调用的方法
 */
public interface ControllerInterface {
    void start();
    void stop();
    void increaseBPM();
    void decreaseBPM();
    void setBPM(int bpm);
}
