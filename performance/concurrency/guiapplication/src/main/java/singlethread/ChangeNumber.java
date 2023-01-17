package singlethread;

import javax.swing.*;
import java.util.Random;

/**
 * @author : anger
 * 任务的声明周期较短
 * 并且只访问 GUI 对象
 * 或者线程封闭或者线程安全的对象
 * 可以在事件线程中完成所有的工作
 */
public class ChangeNumber {
    private static final Random random = new Random();

    public static void showPage() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame jFrame = new JFrame("Change color");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("get random number");
        button.setBounds(100, 100, 65, 30);
        button.addActionListener(actionEvent -> button.setText(String.valueOf(random.nextInt(100))));
        jFrame.add(button);

        jFrame.setSize(300, 250);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        ChangeNumber.showPage();
    }
}
