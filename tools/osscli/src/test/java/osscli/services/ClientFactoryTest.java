package osscli.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import osscli.services.client.ClientFactory;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author : anger
 */
@SpringBootTest
class ClientFactoryTest {

    @Test
    void testFactory() {
        assertSame(ClientFactory.s3Client(), ClientFactory.s3Client());
    }

}