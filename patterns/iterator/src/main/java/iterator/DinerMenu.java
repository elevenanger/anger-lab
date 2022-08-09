package iterator;

import java.util.Iterator;

/**
 * @author Anger
 * created on 2022/8/9
 * 餐厅菜单
 */
public class DinerMenu {
    static final int MAX_ITEMS = 5;
    int numberOfItems = 0;
    MenuItem [] menuItems;

    /**
     * 餐厅菜单实例
     * 在构造函数中初始化列表数据
     */
    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("Vegetarian noodle", "素面", true, 10.00);
        addItem("Seafood noodle", "海鲜面", false, 22.00);
        addItem("Hot dog", "热狗", false, 3.00);
        addItem("Clam soup", "蛤蜊汤", false, 12.00);
        addItem("Vegetarian salad", "素食沙拉", true, 8.00);
    }
    private void addItem(String name, String desc,
                         boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        if (numberOfItems < MAX_ITEMS)
            menuItems[numberOfItems] = menuItem;
        numberOfItems = numberOfItems + 1;
    }

    /**
     * 餐厅菜单是一个菜单元素数组
     * 返回数组的迭代器
     * @return 菜单元素迭代器
     */
    public Iterator<MenuItem> createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}
