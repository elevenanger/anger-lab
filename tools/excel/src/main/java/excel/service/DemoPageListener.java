package excel.service;

import cn.anger.lists.Partition;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * author : anger
 * date : 2022/8/26 18:34
 */
public class DemoPageListener<T> implements ReadListener<T> {

    private List<T> cachedDataList = new ArrayList<>();

    private final int pageSize;

    private final Consumer<List<T>> consumer;

    public DemoPageListener(int pageSize, Consumer<List<T>> consumer) {
        this.pageSize = pageSize;
        this.consumer = consumer;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        cachedDataList.add(data);
        if (cachedDataList.size() >= pageSize) {
            Partition.partition(cachedDataList, 100)
                .parallelStream()
                .forEach(consumer);
            cachedDataList = new ArrayList<>();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!CollectionUtils.isEmpty(cachedDataList))
            consumer.accept(cachedDataList);
    }
}
