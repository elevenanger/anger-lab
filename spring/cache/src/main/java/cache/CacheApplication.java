package cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * author : liuanglin
 * date : 2022/7/6 14:15
 * description : cache demo 应用
 */
@SpringBootApplication
@EnableCaching
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
