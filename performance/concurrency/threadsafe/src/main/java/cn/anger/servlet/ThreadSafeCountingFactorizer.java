package cn.anger.servlet;

import cn.anger.annotation.ThreadSafe;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : anger
 */
@ThreadSafe
public class ThreadSafeCountingFactorizer extends Factorizer {
    private final AtomicLong count = new AtomicLong(0);

    public AtomicLong getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        super.service(req, res);
        count.getAndAdd(1);
    }
}
