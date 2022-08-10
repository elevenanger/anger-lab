package iterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Anger
 * created on 2022/8/10
 */
public class CafeMenu implements Menu {

    Map<String, MenuItem> menuItemMap = new HashMap<>();

    public CafeMenu() {
        addItem("Roaster pork totters", "德国咸猪手", false, 120);
        addItem("Wonton noodles", "云吞面", false, 20);
        addItem("Lettuce", "油麦菜", true, 12);
    }

    private void addItem(String name, String desc,
                         boolean vegetarian, double price) {
        MenuItem item = new MenuItem(name, desc, vegetarian, price);
        menuItemMap.put(name, item);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItemMap.values().iterator();
    }

    public Map<String, MenuItem> getMenuItemMap() {
        return menuItemMap;
    }
}
