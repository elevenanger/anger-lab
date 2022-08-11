package composite;

/**
 * author : liuanglin
 * date : 2022/8/11 22:54
 */
public class Waitress {
    private final MenuComponent allMenu;

    public Waitress(MenuComponent allMenu) {
        this.allMenu = allMenu;
    }

    /**
     * 调用 allMenu 的 print() 方法
     * 打印所有的菜单
     */
    public void printMenu() {
        allMenu.print();
    }
}
