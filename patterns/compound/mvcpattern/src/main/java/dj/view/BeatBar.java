package dj.view;

import javax.swing.*;

/**
 * author : liuanglin
 * date : 2022/8/17 09:46
 */
public class BeatBar extends JProgressBar implements Runnable {
    JProgressBar jProgressBar;
    Thread thread;

    public BeatBar() {
        thread = new Thread(this);
        setMaximum(100);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            int value = getValue();
            value = (int) (value * 0.75);
            repaint();
            try {
                Thread.sleep(50);
            } catch (Exception e){}
        }
    }
}
