package dj.model;

/**
 * author : liuanglin
 * date : 2022/8/17 08:41
 * description : model 接口
 */
public interface BeatModelInterface {
    void initialize();
    void on();
    void off();
    void setBPM(int bpm);
    int getBPM();
    void registerObserver(BeatObserver observer);
    void removeObserver(BeatObserver observer);
    void registerObserver(BPMObserver observer);
    void removeObserver(BPMObserver observer);
}
