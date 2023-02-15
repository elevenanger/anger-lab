package composite;

/**
 * author : anger
 * date : 2022/8/11 22:45
 * description : 菜单元素
 * 叶子实现 Component 接口
 */
public class MenuItem extends MenuComponent {
    private final String name;
    private final String desc;
    private final double price;

    public MenuItem(String name, String desc, double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
            "name='" + name + '\'' +
            ", desc='" + desc + '\'' +
            ", price=" + price +
            '}';
    }
}
