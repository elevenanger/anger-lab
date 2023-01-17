package longrunningtasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : anger
 */
public abstract class Listeners {
    protected static final ExecutorService exec = Executors.newCachedThreadPool();

    public abstract void renderPage();


}