package executorframework.delayedandperiodic;

import java.util.TimerTask;

/**
 * @author : anger
 */
public class ShowTask extends TimerTask {
    public static final String ABNORMAL = "ABNORMAL";
    private final String showMsg;
    public ShowTask(String show) {
        this.showMsg = show;
    }

    @Override
    public void run() {
        if (showMsg.equals(ABNORMAL))
            throw new RuntimeException();
        System.out.println(showMsg);
    }
}
