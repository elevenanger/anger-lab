package dj.controller;

import dj.model.BeatModelInterface;
import dj.view.DJView;

/**
 * author : liuanglin
 * date : 2022/8/17 11:14
 */
public class BeatController implements ControllerInterface {
    BeatModelInterface model;
    DJView view;

    public BeatController(BeatModelInterface model) {
        this.model = model;
        view = new DJView(model, this);
        view.createView();
        view.createControls();
        view.disableStopMenuItem();
        view.disableStartMenuItem();
        model.initialize();
    }

    @Override
    public void start() {
        model.on();
        view.disableStartMenuItem();
        view.enableStopMenuItem();
    }

    @Override
    public void stop() {
        model.off();
        view.disableStopMenuItem();
        view.enableStartMenuItem();
    }

    @Override
    public void increaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm + 1);
    }

    @Override
    public void decreaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm - 1);
    }

    @Override
    public void setBPM(int bpm) {
        model.setBPM(bpm);
    }
}
