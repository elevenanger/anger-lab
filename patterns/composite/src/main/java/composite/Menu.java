package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/8/11 22:49
 * description : 菜单
 * 节点继承 Component 类
 */
public class Menu extends MenuComponent {
    private final List<MenuComponent> menuComponents;
    private final String name;
    private final String desc;

    public Menu(String name, String desc) {
        this.name = name;
        this.desc = desc;
        menuComponents = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    /**
     * print() 是 operation() 方法
     * 因为节点中海油很多叶子节点
     * 所以节点的 print() 方法
     * 除了执行本身的操作
     * 还需要调用所有叶子节点的 print() 方法
     */
    @Override
    public void print() {
        System.out.println(this);
        System.out.println("------------");
        menuComponents.forEach(MenuComponent::print);
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int index) {
        return menuComponents.get(index);
    }

    @Override
    public String toString() {
        return "Menu{" +
            "name='" + name + '\'' +
            ", desc='" + desc + '\'' +
            '}';
    }
}
