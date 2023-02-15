/**
 * author : anger
 * date : 2022/8/14 17:14
 * description : RocketMQ 测试基础信息
 */
public class RocketMQTest {
    protected static final String NAME_SERVER_ADDR = "127.0.0.1:9876";

    protected String generateMessage(int i) {
        return "rocket mq test message : " + i;
    }
}
