package cache.service;

import cache.data.EchoRepository;
import cache.data.po.EchoPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * author : liuanglin
 * date : 2022/7/6 14:27
 * description : 使用缓存
 */
@Service
@Slf4j
public class EchoService implements EchoRepository {

    @Override
    @CacheEvict("echos")
    public EchoPO saveEcho(String echo) {
        return echoPOFunction.apply(echo);
    }

    @Override
    @Cacheable("echos")
    public String getByEcho(String echo) {
        log.info("get echo {}", echo);
        simulateSlowService();
        return echoPOFunction.apply(echo).getEcho();
    }

    private static void simulateSlowService() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    private static final Function<String, EchoPO> echoPOFunction = echo -> {
        EchoPO echoPO = new EchoPO();
        echoPO.setEcho(echo);
        return echoPO;
    };
}
