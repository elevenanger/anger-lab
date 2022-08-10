package iterable;

/**
 * @author Anger
 * created on 2022/8/10
 * 实现 Iterable 接口的对象可以使用 for-each 循环进行遍历
 * Iterable 接口中有一个 Iterator 对象
 * 定义了 default 方法 forEach() 对 Iterator 进行遍历
 */
public interface IterableInterface<T> extends Iterable<T> {
}
