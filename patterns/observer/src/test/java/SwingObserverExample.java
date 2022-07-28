import javax.swing.*;

/**
 * @author Anger
 * created on 2022/7/28
 */
public class SwingObserverExample {
    JFrame jFrame;

    public static void main(String[] args) {
        SwingObserverExample swingObserverExample =
            new SwingObserverExample();
        swingObserverExample.go();
    }

    public void go() {
        jFrame = new JFrame();

        JButton jButton = new JButton("生存还是死亡？");

        /*
        与 update() 相比
        actionPerformed() 在 subject (button) 发生变化时被调用
        */
        jButton.addActionListener(event -> System.out.println("生存"));
        jButton.addActionListener(event -> System.out.println("死亡"));
    }
}
