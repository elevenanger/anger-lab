package iterator;

import java.util.Iterator;

/**
 * @author Anger
 * created on 2022/8/9
 * 餐厅服务员
 */
public class Waitress {
    private final DinerMenu dinerMenu;

    public Waitress(DinerMenu dinerMenu) {
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> menuItemIterator =
                dinerMenu.createIterator();
        menuItemIterator.forEachRemaining(System.out::println);
    }

}
