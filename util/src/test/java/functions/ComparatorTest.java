package functions;

import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * author : anger
 * date : 2022/7/10 10:06
 * description : Comparator 用法测试
 */
class ComparatorTest {
    static Path filePath = Paths.get("./tools/src/test/resources/notice.txt");
    static List<String> words;

    static Random random = new Random();

    static List<ToBeCompared> beCompared ;

    static BiFunction<Boolean, String, ToBeCompared> gen = ToBeCompared::new;

    @BeforeAll
    static void initData() {
        try (Stream<String> lines = Files.lines(filePath)){
            words = lines.map(line -> line.split("\\W"))
                .flatMap(Stream::of)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

            beCompared = IntStream.rangeClosed(0, 100)
                .mapToObj(i ->
                    gen.apply(random.nextBoolean(),
                        words.get(random.nextInt(words.size() - 1))))
                .collect(Collectors.toList());

            System.out.println(beCompared);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Comparator<ToBeCompared> byB = Comparator.comparing(ToBeCompared::isB);

    Comparator<ToBeCompared> byDesc = Comparator.comparing(ToBeCompared::getDesc);

    @Test
    void testCompare() {
        beCompared.sort(byB);
        System.out.println(beCompared);
        beCompared.sort(byDesc);
        System.out.println(beCompared);
        beCompared.sort(byB.reversed().thenComparing(byDesc));
        beCompared.sort(byB.thenComparing(obj -> obj.getDesc().toLowerCase()));
    }
}

@Data
class ToBeCompared {
    final boolean b;
    final String desc;
}