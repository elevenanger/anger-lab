package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author : liuanglin
 * date : 2022/7/28 15:04
 * description : 使用 JMH 做基准测试
 */
@State(Scope.Benchmark)
public class MyBenchmark {

    /*
    使用 JMH 进行基准测试
    首先会执行预热迭代
    预热迭代使得编译器彻底优化代码
    确保报告中的数据都是经过编译器优化的代码执行的性能数据

    然后会看到有不同的 fork 执行操作
    默认将会重复五次测试
    每次 fork 测试都是在一个不同的 JVM 中执行以确保执行结果的可重复性
    每个 JVM 都需要进行预热再执行基准测试
    每次 fork 测试都称之为一次 试验

    最终输出测试结果的摘要数据
    Result "benchmark.MyBenchmark.testIntern":
    609.626 ±(99.9%) 2.588 ops/s [Average]
    (min, avg, max) = (599.877, 609.626, 615.809), stdev = 3.456
    CI (99.9%): [607.037, 612.214] (assumes normal distribution)

    testIntern() 方法每秒钟执行 609 次，置信区间 99.9%
    统计的平均值在每秒 607 次到每秒 612 次之间
     */
    @Benchmark
    @Warmup(time = 3, iterations = 1)
    @Fork(warmups = 1, value = 2)
    @Measurement(time = 3, iterations = 1)
    public void testIntern(Blackhole blackhole) {
        for (int i = 0; i < 100_00; i++) {
            String s = new String("String to intern " + i);
            String t = s.intern();
            blackhole.consume(t);
        }
    }

    /*
    @Param 定义测试的输入范围
    输出不同范围参数的测试结果
    Benchmark                        (nStrings)   Mode  Cnt        Score   Error  Units
    MyBenchmark.testInternWithRange           1  thrpt    2  6370119.411          ops/s
    MyBenchmark.testInternWithRange       10000  thrpt    2      616.117          ops/s
     */
    @Param({"1", "10000"})
    private int nStrings;

    @Benchmark
    @Warmup(time = 3, iterations = 1)
    @Fork(warmups = 1, value = 2, jvmArgs = "-XX:StringTableSize=10000000")
    @Measurement(time = 3, iterations = 1)
    public void testInternWithRange(Blackhole blackhole) {
        for (int i = 0; i < nStrings; i++) {
            String s = "String to intern " + i;
            String t = s.intern();
            blackhole.consume(t);
        }
    }

    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    @Benchmark
    @Warmup(time = 3, iterations = 1)
    @Fork(warmups = 1, value = 2, jvmArgs = "-XX:StringTableSize=10000000")
    @Measurement(time = 3, iterations = 1)
    public void testMap(Blackhole blackhole) {
        for (int i = 0; i < nStrings; i++) {
            String s = "String to intern " + i;
            String t = map.putIfAbsent(s, s);
            blackhole.consume(t);
        }
    }

    private static String[] strings;

    /*
    @Setup 声明需要在基准测试运行前执行的逻辑
    Level.Iteration 指的是每次迭代前后执行该逻辑
    Level.Invocation 指的是在每次方法调用都需要调用该方法
    这将会为 JMH 分析带来较大的阻碍
    仅当在测试耗时非常长的方法才使用这种方式
     */
    @Setup(Level.Iteration)
    public void setUp() {
        strings = new String[nStrings];
        for (int i = 0; i < nStrings; i++) {
            strings[i] = "String to intern " + i;
        }
        map = new ConcurrentHashMap<>();
    }

    @Benchmark
    @Warmup(time = 3, iterations = 1)
    @Fork(warmups = 1, value = 2, jvmArgs = "-XX:StringTableSize=10000000")
    @Measurement(time = 3, iterations = 1)
    public void testInternAfterSetUp(Blackhole blackhole) {
        for (int i = 0; i < nStrings; i++) {
            String t = strings[i].intern();
            blackhole.consume(t);
        }
    }
}
