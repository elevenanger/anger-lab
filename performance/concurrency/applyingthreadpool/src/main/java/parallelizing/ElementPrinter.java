package parallelizing;

import cn.anger.util.concurrency.ThreadUtil;

import java.util.logging.Logger;

/**
 * @author : anger
 */
public class ElementPrinter<T> implements ElementProcessor<T> {
    private final Logger logger = Logger.getAnonymousLogger();
    @Override
    public void process(T element) {
        ThreadUtil.sleep(1);
        logger.info(String.format("element info => %s%n", element.toString()));
    }
}
