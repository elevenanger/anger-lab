package jmm.initializationsafety;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SafeStatesTest {

    @Test
    void visibilityTest() {
        SafeStates states = new SafeStates();
        List<Thread> threads =
            Stream.generate(() -> new Thread(() -> assertEquals("ger1", states.getState("an1"))))
            .limit(10)
            .collect(Collectors.toList());
        ThreadUtil.startAndJoin(threads);
    }

}