package lists;

import cn.anger.util.lists.Partition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * author : anger
 * date : 2022/7/8 15:51
 */
class PartitionTest {
    static List<Integer> list;
    static int partitionSize;
    static int partitionNumber;

    @BeforeAll
    static void prepare() {

        partitionSize = 10;

        list = IntStream.rangeClosed(1, 10002)
            .boxed()
            .collect(Collectors.toList());

        partitionNumber =
            list.size() % partitionSize == 0?
                list.size() / partitionSize:
                    list.size() / partitionSize + 1;
    }

    @Test
    void testPartitionFunctional() {
        List<List<Integer>> lists = (Partition.partitionFunction(list, partitionSize));
        assertEquals(lists.size(), partitionNumber);
    }

    @Test
    void testPartition() {
        List<List<Integer>> lists = (Partition.partition(list, partitionSize));
        assertEquals(lists.size(), partitionNumber);
    }

}