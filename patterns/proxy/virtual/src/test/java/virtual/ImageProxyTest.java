package virtual;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * author : liuanglin
 * date : 2022/8/14 21:14
 * description : 虚拟代理测试
 */
@Slf4j
class ImageProxyTest {

    ImageComponent imageComponent;
    JFrame jFrame = new JFrame("pic");
    JMenuBar menuBar;
    JMenu menu;

    void testVirtualProxy() throws MalformedURLException {
        URL url = new URL("http://images.amazon.com/images/P/B000005IRM.01.LZZZZZZZ.jpg");

        menuBar = new JMenuBar();
        menu = new JMenu("anything");
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);

        JMenuItem item = new JMenuItem("pic");

        menu.add(item);

        item.addActionListener( event -> {
            imageComponent.setIcon(
                new ImageProxy(url));
            jFrame.repaint();
        });

        // 创建一个代理对象
        Icon icon = new ImageProxy(url);
        /*
        将 proxy 对象封装进 ImageComponent 对象
        ImageComponent 被添加进 JFrame 对象
         */
        imageComponent = new ImageComponent(icon);
        jFrame.getContentPane().add(imageComponent);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 600);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) throws MalformedURLException {
        ImageProxyTest test = new ImageProxyTest();
        test.testVirtualProxy();
    }
}