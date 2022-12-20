package cn.anger.serialthreadconfinement;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class ProducerConsumerTest {
    @Test
    void startIndexing() {
        ProducerConsumer.startIndexing(Files.currentFolder());
    }
}