package virtual;

import java.awt.*;

/**
 * author : liuanglin
 * date : 2022/8/14 20:26
 * description : 虚拟代理
 * 图标接口
 */
public interface Icon {
    int getIconWidth();
    int getIconHeight();
    void paintIcon(Component c, Graphics g, int x, int y);
}
