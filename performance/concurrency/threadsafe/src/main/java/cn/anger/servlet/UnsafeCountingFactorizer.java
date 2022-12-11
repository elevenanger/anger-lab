package cn.anger.servlet;

import cn.anger.annotation.NotThreadSafe;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author : anger
 * 在无状态对象的基础上增加一个计数器标识这个 Servlet 的访问量，
 * 相当于在无状态对象上增加了一个有状态的元素，
 * 尽管 ++count 的紧凑语法使其看起来是一个单一的操作，
 * 事实上它不是一个原子操作，
 * 这意味着它不是一个单一的、不可分割的操作，
 * 它是由三个离散的操作组成的操作序列的缩写：
 * - 获取当前值
 * - 值 + 1
 * - 将新值写回原来的值
 * 这是一个 读-改-写 操作的例子
 * 其结果的状态派生自原来的状态
 * UnsafeCountingFactorizer 违反了原子性，所以它不是线程安全的
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends Factorizer {

    private long count = 0;

    public long getCount() {
       return count;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        super.service(req, res);
        count++;
    }
}
