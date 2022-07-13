package lists;

import base.BaseResult;
import base.SingleInt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class ForkJoinListTest {

    private static final List<SingleInt> list =
            IntStream.rangeClosed(0, 30)
                    .mapToObj(SingleInt::new)
                    .collect(Collectors.toList());

    @Test
    void test() {
        ForkJoinTask<List<BaseResult>> results = new ForkJoinList<>(list);
        results.invoke().forEach(result -> log.info(String.valueOf(result.isResult())));
    }

}