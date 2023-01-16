package parallelizing.recursive;

import java.util.List;

/**
 * @author : anger
 */
public interface Node<T> {
    T compute();
    List<Node<T>> getChildren();
}
