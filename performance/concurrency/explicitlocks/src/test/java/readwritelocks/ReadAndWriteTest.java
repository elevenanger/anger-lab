package readwritelocks;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import cn.anger.util.random.Random;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class ReadAndWriteTest {

    private ReadAndWrite readAndWrite = new ReadAndWrite();

    @Test
    void add() {
        ConcurrentWorkStream
            .heavyWorkStream(() -> readAndWrite.add(Random.getRandom())).doWork();
    }

    @Test
    void compareSize() {
        ConcurrentWorkStream.heavyWorkStream(
            () -> readAndWrite.sizeCompare()).doWork();
    }

    @Test
    void synchronizedAdd() {
        ConcurrentWorkStream.heavyWorkStream(
            () -> readAndWrite.synchronizedAdd(Random.getRandom())).doWork();
    }

    @Test
    void synchronizedSizeCompare() {
        ConcurrentWorkStream.heavyWorkStream(
            () -> readAndWrite.synchronizedSizeCompare()).doWork();
    }
}