package longrunningtasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
public class RunInBackGround extends Listeners {
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

        runInBackground(() -> System.out.println(LocalDate.now()));

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

    private void runInBackground(final Runnable task) {
        startButton.addActionListener(e -> {
            class CancelListener implements ActionListener {
                BackGroundTask<?> task;
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (task != null)
                        task.cancel(true);
                }
            }

            final CancelListener listener = new CancelListener();
            listener.task = new BackGroundTask<Void>() {
                @Override
                protected Void compute() throws Exception {
                    while (moreWork() && !isCancelled())
                        doSomeWork();
                    return null;
                }
                private final AtomicInteger workCount = new AtomicInteger(0);
                private boolean moreWork() {
                    return workCount.get() < 100;
                }
                private void doSomeWork() {
                    System.out.printf("run in background do work %d times %n", workCount.getAndIncrement());
                }
            };
            cancelButton.addActionListener(listener);
            exec.execute(task);
        });
    }

    public static void main(String[] args) {
        RunInBackGround runInBackGround = new RunInBackGround();
        runInBackGround.renderPage();
    }
}
