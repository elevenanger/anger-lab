package cn.anger.independentvariable;

import cn.anger.annotation.ThreadSafe;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : anger
 * 一个类的状态由多个互相独立的线程安全的字段组成
 * 那么这个类也是线程安全的
 */
@ThreadSafe
public class IndependentComponent {
    // 两个 list 之间没有存在任何重合，它们之间是互相独立的
    // IndependentComponent 将它的线程安全职责委派给了这两个独立的 list
    private final List<MouseListener> mouseListeners =
        new CopyOnWriteArrayList<>();
    private final List<KeyListener> keyListeners =
        new CopyOnWriteArrayList<>();

    public void addMouseListener(MouseListener mouseListener) {
        mouseListeners.add(mouseListener);
    }

    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    public void removeMouseListener(MouseListener mouseListener) {
        mouseListeners.remove(mouseListener);
    }

    public void removeKeyListener(KeyListener keyListener) {
        keyListeners.remove(keyListener);
    }
}