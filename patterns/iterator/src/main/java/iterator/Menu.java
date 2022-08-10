package iterator;

import java.util.Iterator;

/**
 * @author Anger
 * created on 2022/8/10
 * 菜单接口
 * 定义方法
 * 创建菜单元素迭代器
 */
public interface Menu {
    Iterator<MenuItem> createIterator();
}
