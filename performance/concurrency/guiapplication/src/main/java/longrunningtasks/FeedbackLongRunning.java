package longrunningtasks;

import cn.anger.concurrency.ThreadUtil;
import singlethread.GuiExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
public class FeedbackLongRunning extends Listeners {
    private final JFrame frame = new JFrame("feedback long running");
    private final JButton button  = new JButton("Do");
    private final JTextField textField = new JTextField("result");
    private final AtomicInteger count = new AtomicInteger();

    @Override
    public void renderPage() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        button.addActionListener(e -> longRunningTaskWithFeedBack());

        JPanel panelText = new JPanel();
        JPanel panelButton = new JPanel();

        panelText.add(textField);
        panelButton.add(button);

        frame.getContentPane().add(panelText, BorderLayout.SOUTH);
        frame.getContentPane().add(panelButton, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    /**
     * 有反馈的长耗时任务
     */
    private void longRunningTaskWithFeedBack() {
        button.setEnabled(false);
        textField.setText("busy");
        exec.execute(() -> {
            try {
                ThreadUtil.sleep(500);
                textField.setText(String.format("%d", count.getAndIncrement()));
                ThreadUtil.sleep(500);
            } finally {
                GuiExecutor.instance().execute(() -> {
                    button.setEnabled(true);
                    textField.setText("idle");
                });
            }
        });
    }

    public static void main(String[] args) {
        FeedbackLongRunning feedbackLongRunning = new FeedbackLongRunning();
        feedbackLongRunning.renderPage();
    }
}
