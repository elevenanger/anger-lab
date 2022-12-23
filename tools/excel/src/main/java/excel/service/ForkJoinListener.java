package excel.service;

import cn.anger.lists.ForkJoinList;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * author : liuanglin
 * date : 2022/8/26 21:03
 * description : 使用递归多线程处理数据
 */
public class ForkJoinListener<T> implements ReadListener<T> {

    private List<T> cachedDataList = new ArrayList<>();

    private final int pageSize;

    private final Consumer<List<T>> consumer;

    public ForkJoinListener(int pageSize, Consumer<List<T>> consumer) {
        this.pageSize = pageSize;
        this.consumer = consumer;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        cachedDataList.add(data);
        if (cachedDataList.size() >= pageSize) {
            ForkJoinList<T> list = new ForkJoinList<>(cachedDataList, consumer);
            list.invoke();
            cachedDataList = new ArrayList<>();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!CollectionUtils.isEmpty(cachedDataList))
            consumer.accept(cachedDataList);
    }
}
