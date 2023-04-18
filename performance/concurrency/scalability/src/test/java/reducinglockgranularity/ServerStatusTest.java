package reducinglockgranularity;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author : anger
 */
class ServerStatusTest {
    private Set<String> users;
    private Set<String> queries;
    private ServerStatus serverStatus;
    private FineGrainedServerStatus fineGrainedServerStatus;
    private final Random random = new Random();
    private final Supplier<String> randomName =
        () -> String.format("anger%d", random.nextInt());
    private final Supplier<String> randomQuery =
        () -> String.format("select * from user where username = anger%d", random.nextInt());

    @BeforeEach
    void setUp() {
        users = new HashSet<>();
        queries = new HashSet<>();
    }

    @Test
    void testServerStatus() {
        serverStatus = new ServerStatus(users, queries);
        ConcurrentWorkStream.commonWorkStreams(
            () -> serverStatus.addUser(randomName.get()),
            () -> serverStatus.addQuery(randomQuery.get())
        ).doWork();
    }

    @Test
    void testFineGrainedServerStatus() {
        fineGrainedServerStatus = new FineGrainedServerStatus(users, queries);
        ConcurrentWorkStream.commonWorkStreams(
            () -> fineGrainedServerStatus.addUser(randomName.get()),
            () -> fineGrainedServerStatus.addQuery(randomQuery.get())
        ).doWork();
    }
}