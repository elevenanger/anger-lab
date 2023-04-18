package labutils.lazyinit;

/**
 * @author : anger
 */
public abstract class LazyInit<T> {

    protected T expensiveInstance = null;

    abstract T getInstance();
}
