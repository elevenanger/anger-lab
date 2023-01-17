package longrunningtasks;

import cn.anger.concurrency.ThreadUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author : anger
 */
public class LongRunning extends Listeners {
    private final Random random = new Random();

    @Override
    public void renderPage() {
        JFrame frame = new JFrame("Random color");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel panelText = new JPanel();
        JPanel panelButton = new JPanel();

        JTextField textField = new JTextField("long running task");

        JButton computeButton = new JButton("do computation");
        computeButton.addActionListener(e -> exec.execute(
            // 执行耗时较长的任务
            () -> {
                ThreadUtil.sleep(10);
                String text = String.format("%.2f%n", random.nextFloat());
                textField.setText(text);
            }));

        panelText.add(textField);
        panelButton.add(computeButton);

        frame.getContentPane().add(panelText, BorderLayout.SOUTH);
        frame.getContentPane().add(panelButton, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        LongRunning longRunning = new LongRunning();
        longRunning.renderPage();
    }
}
