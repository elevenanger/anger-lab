package cache.service;

import cache.data.po.EchoPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/7/6 14:37
 */
@SpringBootTest
@Slf4j
class EchoServiceTest {

    @Autowired
    EchoService echoService;

    @Test
    void echoCacheTest() {
        EchoPO echo = echoService.saveEcho("hello");
        assertEquals("hello", echo.getEcho());
    }

    @Test
    void getEchoTest() {
        log.info("init");
        log.info("first get: " + echoService.getByEcho("hello"));
        log.info("second get: " + echoService.getByEcho("hello"));
        log.info("save flush cache: " + echoService.saveEcho("hello"));
        log.info("get after flush: " + echoService.getByEcho("hello"));
    }
}