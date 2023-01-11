package sync;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * author : liuanglin
 * date : 2022/8/14 17:04
 * description : rocketmq 同步模式生产者
 */
public class SyncProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("sync_producer");
    }
}
