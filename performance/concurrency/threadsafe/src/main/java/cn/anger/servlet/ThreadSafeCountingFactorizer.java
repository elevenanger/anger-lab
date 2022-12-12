package cn.anger.servlet;

import cn.anger.annotation.ThreadSafe;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : anger
 * Servlet 的状态是 count 的状态，
 * 将 count 替换为 AtomicLong 类型，
 * 使用原子变量来确保访问 servlet 状态的原子性,
 * 因为 count 是线程安全的，
 * 所以 Servlet 是线程安全的。
 */
@ThreadSafe
public class ThreadSafeCountingFactorizer extends Factorizer {
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        super.service(req, res);
        count.getAndAdd(1);
    }
}
