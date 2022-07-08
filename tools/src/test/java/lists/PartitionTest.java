package lists;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/7/8 15:51
 * description :
 */
class PartitionTest {

    List<Integer> list = IntStream.rangeClosed(1, 102).boxed().collect(Collectors.toList());

    @Test
    void testPartition() {
        int partitionSize = 10;
        List<List<Integer>> lists = (Partition.partition(list, partitionSize));
        lists.forEach(System.out::println);
        int partitions = list.size() / partitionSize;
        assertEquals(lists.size(), list.size() % partitions ==0? partitions:(partitions + 1));
    }

}