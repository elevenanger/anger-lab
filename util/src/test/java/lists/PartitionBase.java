package lists;

import cn.anger.lists.Partition;
import com.google.common.collect.Lists;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author : anger
 * date : 2022/7/10 20:19
 * description : Partition 基准测试
 */
@State(Scope.Thread)
public class PartitionBase {

    static List<Integer> list;
    static int partitionSize;
    static int partitionNumber;


    @Setup
    public static void prepare() {

        partitionSize = 10;

        list = IntStream.rangeClosed(1, 10002)
            .boxed()
            .collect(Collectors.toList());

        partitionNumber =
            list.size() % partitionSize == 0?
                list.size() / partitionSize:
                list.size() / partitionSize + 1;
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2, time = 1)
    @Measurement(iterations = 2, time = 2)
    @Threads(4)
    public void partitionFunctionalBase() {
        Partition.partitionFunction(list, partitionNumber);
    }


    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2, time = 1)
    @Measurement(iterations = 2, time = 2)
    @Threads(4)
    public void partitionBase() {
        Partition.partition(list, partitionSize);
    }


    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2, time = 1)
    @Measurement(iterations = 2, time = 2)
    @Threads(4)
    public void guavaPartitionBase() {
        Lists.partition(list, partitionSize);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 2, time = 1)
    @Measurement(iterations = 2, time = 2)
    @Threads(4)
    public void innerPartitionBase() {
        Partition.partitionByInner(list, partitionSize);
    }
}
