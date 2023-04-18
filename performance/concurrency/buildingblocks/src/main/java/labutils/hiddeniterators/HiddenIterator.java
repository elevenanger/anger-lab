package labutils.hiddeniterators;

import cn.anger.util.concurrency.ThreadUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author : anger
 */
public class HiddenIterator {
    private final Set<Integer> set = new HashSet<>();
    public synchronized void add(Integer i) { set.add(i); }
    public synchronized void remove(Integer i) { set.remove(i); }
    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            ThreadUtil.sleep(10);
            add(r.nextInt());
        }
        System.out.println("added ten elements to set." + set);
    }
}
