package collectors;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

/**
 * author : liuanglin
 * date : 2022/7/7 09:20
 * description : Collector 分组器测试
 */
@Slf4j
class GroupingTest {

    private final List<Helper> helpers =
        IntStream.rangeClosed(0, 100)
            .mapToObj(Helper::new)
            .collect(Collectors.toList());

    IntFunction<Function<Helper, Integer>> partitionNumber =
        number -> helper -> helper.getI() % number;

    @Test
    void testGroup() {
        Map<Integer, List<Helper>> groupByNumber =
        helpers.stream().collect(groupingBy(partitionNumber.apply(8)));
        log.info(groupByNumber.toString());
    }
}
