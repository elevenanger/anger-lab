package cn.anger.threadconfinement.threadlocal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : anger
 */
class ConnectionDispenserTest {

    @Test
    void testGetConnection() throws ExecutionException, InterruptedException {
        Connection connection1;
        Connection connection2;

        connection1 = ConnectionDispenser.getConnection();

        FutureTask<Connection> getAnotherThreadConnection =
            new FutureTask<>(() -> ConnectionDispenser.getConnection());

        new Thread(getAnotherThreadConnection).start();

        connection2 = getAnotherThreadConnection.get();

        Assertions.assertNotEquals(connection1, connection2);
    }

}