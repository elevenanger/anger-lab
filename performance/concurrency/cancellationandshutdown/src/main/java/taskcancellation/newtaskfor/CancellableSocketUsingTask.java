package taskcancellation.newtaskfor;

/**
 * @author : anger
 */
public class CancellableSocketUsingTask<T> extends SocketUsingTask<T> {

    @Override
    public T call() throws Exception {
        readInput();
        return null;
    }
}
