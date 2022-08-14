import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * author : liuanglin
 * date : 2022/8/14 17:18
 * description : 同步信息测试
 */
@Slf4j
class AsyncMessageTest extends RocketMQTest {
    private static final int MESSAGE_COUNT = 100;
    private static final String PRODUCER_GROUP = "async_massage_producer";
    private static final String CONSUMER_GROUP = "async_message_consumer";
    private static final String TOPIC = "NormalMessageTopic";
    private final static String TAG = "TagA";
    private DefaultMQProducer producer;
    private Random random;

    @Test
    void produceMessageTest() throws MQClientException {
        log.info("start mq producer");
        producer = new DefaultMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        sendMessage();
        shutdownService();
    }

    void sendMessage() {
        log.info("发送信息");
        IntStream.rangeClosed(1, MESSAGE_COUNT)
            .mapToObj(this::generateMessage)
            .forEach(this::sendMessageSync);
    }

    void shutdownService() {
        log.info("停止 producer 服务：{}", producer.getInstanceName());
        producer.shutdown();
    }

    void sendMessageAsync(String messageText) {

        Message message = new Message(TOPIC, TAG, messageText.getBytes(StandardCharsets.UTF_8));
        try {
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("消息发送成功：{}", messageText);
                }

                @Override
                public void onException(Throwable e) {
                    log.info("消息发送失败：{}", messageText);
                }
            });
        } catch (MQClientException | RemotingException |
                 InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void sendMessageSync(String messageText) {
        Message message = new Message(TOPIC, TAG, messageText.getBytes(StandardCharsets.UTF_8));
        try {
            SendResult result = producer.send(message);
            log.info("消息发送结果：{}", result);
        } catch (MQClientException | RemotingException |
                 MQBrokerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void consumeMessage() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);

        consumer.setNamesrvAddr(NAME_SERVER_ADDR);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(TOPIC, "*");

        consumer.registerMessageListener(
            (MessageListenerConcurrently) (msg, context) -> {
                log.info("{} 收到信息：{}", Thread.currentThread().getName(), msg);
                try {
                    Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        );

        consumer.start();
        log.info("消费者已启动：{}", consumer.getInstanceName());
    }

    public static void main(String[] args) throws MQClientException {
        AsyncMessageTest test = new AsyncMessageTest();
        test.consumeMessage();
    }
}
