package composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/8/11 22:58
 */
class MenuComponentTest {

    @Test
    void testOperation() {
        // 创建节点
        MenuComponent wanWei =
            new Menu("Wan Wei", "港式茶餐厅");

        // 创建子节点
        MenuComponent drink =
            new Menu("Drink", "饮料菜单");

        // 创建子节点
        MenuComponent westFood =
            new Menu("West Food", "西餐");

        // 往子节点中添加元素
        drink.add(new MenuItem("Mint Tea", "薄荷茶", 12));
        drink.add(new MenuItem("Hong Kong Milk Tea", "港式奶茶", 15));

        westFood.add(new MenuItem("Salty Pork Totters", "咸猪手", 120));
        westFood.add(new MenuItem("West Toast", "西多士", 10));

        // 将子节点添加到上层的节点中
        westFood.add(drink);
        wanWei.add(westFood);

        // client
        Waitress waitress = new Waitress(wanWei);
        waitress.printMenu();
    }
}