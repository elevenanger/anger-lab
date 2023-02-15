package virtual;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * author : anger
 * date : 2022/8/14 21:03
 * description : 图形代理
 * 实现 Subject（Icon） 接口
 * ImageIcon 对象是实际需要展示的 Icon 对象
 * 虚拟代理：
 * 虚拟代理代理实例化开销比较大的对象
 * 虚拟代理一般会延迟对象的实例化操作直至它真的被需要
 */
public class ImageProxy implements Icon {

    volatile ImageIcon imageIcon;
    private final URL imageUrl;
    Thread retrievalThread;
    boolean retrieving = false;

    public ImageProxy(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int getIconWidth() {
        if (imageIcon != null)
            return imageIcon.getIconWidth();
        return 800;
    }

    @Override
    public int getIconHeight() {
        if (imageIcon != null)
            return imageIcon.getIconHeight();
        return 600;
    }

    synchronized void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (imageIcon != null)
            imageIcon.paintIcon(c, g, x, y);
        else {
            g.drawString("加载图形中...", x + 300, y + 300);
            if (!retrieving) {
                retrieving = true;
                retrievalThread = new Thread( () -> {
                        setImageIcon(new ImageIcon(imageUrl, "图形"));
                        c.repaint();
                    });
                retrievalThread.start();
            }
        }
    }
}
