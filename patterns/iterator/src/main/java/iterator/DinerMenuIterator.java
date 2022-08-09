package iterator;

import java.util.Iterator;

/**
 * @author Anger
 * created on 2022/8/9
 * 迭代器模式：
 * 迭代器模式实现Iterator接口
 * 迭代器模式封装了集合迭代的过程
 * 迭代器的底层迭代对象可能是数组、列表、Map 等各种类型的集合
 * 使用方使用的是 Iterator 接口
 * 迭代器的使用方无需处理或者关心底层的数据结构和迭代的过程
 */
public class DinerMenuIterator implements Iterator<MenuItem> {

    private final MenuItem[] items;

    /*
    位置信息
    维护迭代的位置
     */
    int position = 0;

    /**
     * 将需要迭代的 MenuItem 数组作为构造函数的参数
     * @param items 需要迭代的数组
     */
    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    /**
     * 迭代器模式的两个抽象方法
     * haxNext() 返回一个布尔值标识还有没有可以迭代的元素
     * next() 返回下一个元素
     * @return 是否还有剩余的元素
     */
    @Override
    public boolean hasNext() {
        return position < items.length &&
                items[position] != null;
    }

    /**
     * 返回下一个元素
     * @return 下一个元素
     */
    @Override
    public MenuItem next() {
        MenuItem thisItem = items[position];
        position ++;
        return thisItem;
    }
}
