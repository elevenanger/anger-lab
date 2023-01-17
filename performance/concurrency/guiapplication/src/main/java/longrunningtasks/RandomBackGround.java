package longrunningtasks;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author : anger
 * 随机颜色的背景
 */
public class RandomBackGround extends Listeners {
    private final Random random = new Random();
    @Override
    public void renderPage() {
        JFrame frame = new JFrame("Random color");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(200, 100);

        JButton colorButton = new JButton("Change color");
        colorButton.addActionListener(e ->
            panel.setBackground(new Color(random.nextInt())));
        colorButton.setSize(100, 20);

        panel.add(colorButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        RandomBackGround randomBackGround = new RandomBackGround();
        randomBackGround.renderPage();
    }

}
