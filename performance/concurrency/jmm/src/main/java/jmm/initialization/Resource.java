package jmm.initialization;

import cn.anger.util.concurrency.ThreadUtil;

/**
 * @author : anger
 */
public class Resource {
    public Resource() {
        ThreadUtil.sleep(10);
    }
}