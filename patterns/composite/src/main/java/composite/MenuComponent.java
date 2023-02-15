package composite;

/**
 * author : anger
 * date : 2022/8/11 21:36
 * description : 菜单元素接口
 * 组合模式：
 * 使用组合模式可以用统一的方式处理整个树状结构或者其中的某一个对象
 * 组合模式是一种树状的结构
 * 树状的机构包括节点和叶子
 * 可以对节点和叶子使用相同的操作
 * 换句话说：可以忽略叶子和节点的区别
 * 组合模式由以下角色构成：
 * 1、组件接口：
 *    所有的叶子和节点都实现组件接口
 *    组件接口包括 add() remove() getChild() 以及组件的操作 operation()
 *    组件结构会提供这些方法默认的实现
 *    叶子和节点可以按实际需要重写其中的方法
 * 2、叶子：
 *    叶子只重写 operation() 方法，因为其它的方法叶子节点并不需要
 * 3、节点：
 *    节点实现接口中节点操作和组件操作方法
 *    节点本身可以具备组件的行为 （operation()）
 *    节点也可以拥有子节点（操作节点方法）
 */
public abstract class MenuComponent {

    String getName() {
        throw new UnsupportedOperationException();
    }

    double getPrice() {
        throw new UnsupportedOperationException();
    }

    String getDesc() {
        throw new UnsupportedOperationException();
    }

    /**
     * 所有的节点和叶子都需要实现 Component 接口
     * 但是角色不同需要实现的方法也有所不同
     * 所以所有方法的默认实现都抛出一个运行时异常
     */
    void print() {
        throw new UnsupportedOperationException();
    }

    void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    void remove(MenuComponent menuComponent) {
        throw  new UnsupportedOperationException();
    }

    MenuComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }
}
