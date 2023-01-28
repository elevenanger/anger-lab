package reducinglockgranularity;

/**
 * @author : anger
 * 使用分离锁实现 Map
 */
public class StripedMap<K, V> {
    // 锁数量
    private static final int N_LOCKS = Runtime.getRuntime().availableProcessors();
    // hash bucket
    private final Node<K, V>[] buckets;
    // 分离锁数组
    private final Object[] locks;

    private static class Node<K, V> {
        Node<K, V> next;
        K key;
        V value;
    }

    public StripedMap(int numBuckets) {
        // 初始化 bucket
        buckets = new Node[numBuckets];
        // 初始化分离锁
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private final int hash(K key) {
        return key.hashCode() % buckets.length;
    }

    public V get(K key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node<K, V> m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key))
                    return m.value;
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }
}
