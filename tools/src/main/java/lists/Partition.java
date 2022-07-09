package lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


}
