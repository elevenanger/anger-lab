package virtual;

import javax.swing.*;
import java.awt.*;

/**
 * author : liuanglin
 * date : 2022/8/14 20:54
 * description : 图形
 */
public class ImageComponent extends JComponent {
    private transient Icon icon;

    public ImageComponent(Icon icon) {
        this.icon = icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        int x = (800 - w) / 2;
        int y = (600 - h) / 2;
        icon.paintIcon(this, g, x, y);
    }
}
