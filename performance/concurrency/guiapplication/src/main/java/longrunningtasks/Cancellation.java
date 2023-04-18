package longrunningtasks;

import cn.anger.util.concurrency.ThreadUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
public class Cancellation extends Listeners {
    private final JButton startButton = new JButton("start");
    private final JButton cancelButton = new JButton("cancel");
    private final JFrame frame = new JFrame("cancellation");
    private final JTextField textField = new JTextField("computation result");
    Future<?> runningTask = null; // 线程封闭

    @Override
    public void renderPage() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        taskWithCancellation();

        JPanel panelText = new JPanel();
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BorderLayout());

        panelText.add(textField);
        panelButton.add(startButton, BorderLayout.WEST);
        panelButton.add(cancelButton, BorderLayout.EAST);

        frame.getContentPane().add(panelText, BorderLayout.SOUTH);
        frame.getContentPane().add(panelButton, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    /**
     * 可取消的任务
     */
    private void taskWithCancellation() {
        runningTask = new FutureTask<>(() -> {
            System.out.println("task with cancellation");
            return null;
        });
        // 任务开始
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (moreWork()) {
                    if (Thread.currentThread().isInterrupted()) {
                        cleanUpPartialWork();
                        break;
                    }
                    doSomeWork();
                }
            }

            private final AtomicInteger workCount = new AtomicInteger(0);
            private boolean moreWork() {
                return workCount.get() < 100;
            }

            private void cleanUpPartialWork() {
                System.out.println("Partial work cleaned up");
            }

            private void doSomeWork() {
                ThreadUtil.sleep(100);
                System.out.printf("do work %d times %n", workCount.getAndIncrement());
            }
        });

        cancelButton.addActionListener(e -> {
            if (runningTask != null)
                runningTask.cancel(true);
        });
    }

    public static void main(String[] args) {
        Cancellation cancellation = new Cancellation();
        cancellation.renderPage();
    }

}
