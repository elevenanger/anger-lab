package dj.model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/8/17 09:07
 */
public class BeatModel implements BeatModelInterface, Runnable {
    List<BeatObserver> beatObservers = new ArrayList<>();
    List<BPMObserver> bpmObservers = new ArrayList<>();
    int bpm = 90;
    Thread thread;
    boolean stop = false;
    Clip clip;

    @Override
    public void initialize() {
        try {
            File resource = new File("/Users/liuanglin/data/bsj.wav");
            clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
            clip.open(AudioSystem.getAudioInputStream(resource));
        } catch (UnsupportedAudioFileException |
                 LineUnavailableException |
                 IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void on() {
        bpm = 90;
        notifyBPMObservers();
        thread = new Thread(this);
        stop = false;
        thread.start();
    }

    @Override
    public void off() {
        stopBeat();
        stop = true;
    }

    @Override
    public void setBPM(int bpm) {
        this.bpm = bpm;
        notifyBPMObservers();
    }

    @Override
    public int getBPM() {
        return bpm;
    }

    @Override
    public void registerObserver(BeatObserver observer) {
        beatObservers.add(observer);
    }

    @Override
    public void removeObserver(BeatObserver observer) {
        beatObservers.remove(observer);
    }

    public void notifyBeatObservers() {
        beatObservers.forEach(BeatObserver::updateBeat);
    }

    @Override
    public void registerObserver(BPMObserver observer) {
        bpmObservers.add(observer);
    }

    @Override
    public void removeObserver(BPMObserver observer) {
        bpmObservers.remove(observer);
    }

    public void notifyBPMObservers() {
        bpmObservers.forEach(BPMObserver::updateBPM);
    }

    @Override
    public void run() {
        while (! stop) {
            playBeat();
            notifyBeatObservers();
            try {
                Thread.sleep(600_00/getBPM());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void playBeat() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void stopBeat() {
        clip.setFramePosition(0);
        clip.stop();
    }
}
