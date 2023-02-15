package collectors;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * author : anger
 * date : 2022/7/7 08:41
 * description : 测试分区收集器
 */
@Slf4j
class PartitionTest {

    private final List<Helper> originList =
        IntStream
            .rangeClosed(0, 10)
            .mapToObj(Helper::new)
            .collect(toList());

    private final Predicate<Helper> byNumber = helper -> helper.getI() % 2 == 0;

    @Test
    void testPartitionByNumber () {
        Map<Boolean, List<Helper>> map = originList.stream().collect(partitioningBy(byNumber));
        log.info(map.toString());
    }

    @Test
    void testPartitionByNumberMax () {
        Map<Boolean, Optional<Helper>> map =
            originList.stream()
                .collect(partitioningBy((byNumber), maxBy(Helper::compareTo)));
        assertEquals(9, map.get(false).get().getI());
        assertEquals(10, map.get(true).get().getI());
    }
}

@Data
class Helper implements Comparable<Helper>{
    private final int i;

    @Override
    public int compareTo(Helper o) {
        return this.getI() - o.getI();
    }
}