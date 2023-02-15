package streams;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * author : anger
 * date : 2022/7/12 19:00
 * description :
 */
@Slf4j
class ForkJoinSumCalculatorTest {

    @Test
    void forkJoinSumTest() {
        long[] longArr = LongStream.rangeClosed(0L, 100_000L).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(longArr);
        log.info(new ForkJoinPool().invoke(task).toString());
    }

}