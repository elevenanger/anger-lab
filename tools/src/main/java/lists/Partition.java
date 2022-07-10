package lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/7/8 15:17
 * description : List 分区工具类
 */
public class Partition {

    private Partition(){}

    public static <T> List<List<T>> partition(List<T> originList, int partitionSize) {
        int size = originList.size();
        if (partitionSize <= 0 || partitionSize >= size)
            return Collections.singletonList(originList);

        List<List<T>> partitionedList = new ArrayList<>();
        int start = 0;
        int end = partitionSize;
        while (end < size) {
            partitionedList.add(originList.subList(start, end));
            start += partitionSize;
            end += partitionSize;
            end = Math.min(end, size);
        }
        partitionedList.add(originList.subList(start, end));
        return partitionedList;
    }

    public static <T> List<List<T>> partitionFunction(List<T> list, int partSize) {
        final BiFunction<List<T>, Integer, List<List<T>>> partitionFunc =
            (origin, partitionSize) -> {
                final int size = origin.size();
                final int[] initIndex = {0, partitionSize > size?size:partitionSize};
                final int partitionNumber =
                    size % partitionSize == 0?
                        size / partitionSize:
                        size / partitionSize + 1;
                final UnaryOperator<int []> getIndexPair =
                    intArr -> new int[] {intArr[1], Math.min(intArr[1] + partitionSize, size)};
                final Function<int[], List<T>> getPartitionListByIndexPair =
                    intArr -> origin.subList(intArr[0], intArr[1]);
                return Stream.iterate(initIndex, getIndexPair)
                    .map(getPartitionListByIndexPair)
                    .limit(partitionNumber)
                    .collect(Collectors.toList());
            };
        return partitionFunc.apply(list, partSize);
    }

}
