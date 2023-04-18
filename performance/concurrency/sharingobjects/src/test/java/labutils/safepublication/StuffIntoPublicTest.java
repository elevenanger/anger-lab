package labutils.safepublication;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
class StuffIntoPublicTest {

    private final StuffIntoPublic stuff = new StuffIntoPublic();
    @Test
    void unsafePublishTest() {
        stuff.toHold();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                stuff.toHold();
                stuff.holder.assertSanity();
            }
        };

        List<Thread> threads = Stream.generate(() -> new Thread(runnable))
            .limit(10)
            .collect(Collectors.toList());
        threads.forEach(Thread::start);

        runnable.run();

    }
}