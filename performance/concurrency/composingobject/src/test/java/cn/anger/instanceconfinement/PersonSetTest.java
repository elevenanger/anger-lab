package cn.anger.instanceconfinement;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author : anger
 */
class PersonSetTest {
    private final PersonSet personSet = new PersonSet();

    @Test
    void testThreadSafe() throws IOException {
        Path path =
            Paths.get("performance/concurrency/composingobject/src/test/java/cn/anger/instanceconfinement/name.txt");
        List<String> strings = Files.readAllLines(path);
        strings.stream().parallel()
            .map(s -> s.split(" ")[0].trim())
            .filter(s -> !s.isEmpty())
            .map(Person::new)
            .forEach(personSet::addPerson);
        System.out.println(personSet.numberOfPerson());
    }

}