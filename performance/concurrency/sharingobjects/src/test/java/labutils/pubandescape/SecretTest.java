package labutils.pubandescape;

import labutils.pubandescape.publicstaticescape.Secret;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * @author : anger
 */
class SecretTest {

    final Secret secret = new Secret();

    @Test
    void testUnsafePublicStaticPublication() {
        Callable<Set<Secret>> extractKnownSecret = () -> {
            secret.initialize();
            return Secret.knownSecret;
        };

        FutureTask<Set<Secret>> extractTask1 = new FutureTask<>(extractKnownSecret);
        FutureTask<Set<Secret>> extractTask2 = new FutureTask<>(extractKnownSecret);

        Thread t1 = new Thread(extractTask1);
        Thread t2 = new Thread(extractTask2);

        t1.start();
        t2.start();

        Set<Secret> secrets1;
        Set<Secret> secrets2;

        try {
            t1.join();
            t2.join();

            secrets1 = extractTask1.get();
            secrets2 = extractTask2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(secrets1);
        assertNotNull(secrets2);
        assertNotSame(secrets1, secrets2);

    }

}