package labutils.cache;

/**
 * @author : anger
 * Computable 表示耗时较长的计算任务
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
