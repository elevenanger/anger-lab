package iterator;

import java.util.Iterator;

/**
 * @author Anger
 * created on 2022/8/9
 * 餐厅服务员
 */
public class Waitress {
    private final Menu menu;

    public Waitress(Menu menu) {
        this.menu = menu;
    }

    public void printMenu() {
        Iterator<MenuItem> menuItemIterator =
                menu.createIterator();
        menuItemIterator.forEachRemaining(System.out::println);
    }

}
