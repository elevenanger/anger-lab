package labutils.lazyinit;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : anger
 */
public class ExpensiveObject {

    private static final Path path =
        Paths.get("performance", "concurrency", "threadsafe", "src", "main", "resources", "numbers.dat")
                .toAbsolutePath();

    public ExpensiveObject() {
        readLargeData();
    }

    private List<String> largeData = null;

    public List<String> getLargeData() {
        return largeData;
    }

    private void readLargeData() {
        try {
            String thread = Thread.currentThread().getName();
            System.out.println(thread + " Reading Data ...");
            Thread.sleep(200);
            largeData = Files.readAllLines(path);
            System.out.println(thread + " Read finished.");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        List<String> numbers =
            IntStream.rangeClosed(10_000, 20_000)
                .mapToObj(i -> i + " ")
                .collect(Collectors.toList());

        try {
            Files.write(path, numbers, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
