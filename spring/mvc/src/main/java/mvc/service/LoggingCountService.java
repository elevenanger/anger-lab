package mvc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author : liuanglin
 * date : 2022/7/22 22:00
 * description : 登录计数服务
 * 注解 @ApplicationScope bean
 * 在 spring 上下文中只会存在一个该类型实例
 * 任何客户端的所有 HTTP 请求都共享这个实例
 * 类似于 Singleton bean
 * 但是 @ApplicationScope bean 同一类型只会在 spring 上下文中存在一个实例
 */
@Service
@ApplicationScope
@Slf4j
public class LoggingCountService {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.getAndIncrement();
        log.info("尝试登录次数：{}", count);
    }

    public int getCount() {
        return count.get();
    }
}
