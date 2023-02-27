package osscli.services.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Anger
 * created on 2023/2/27
 */
class ConfigurationStoreTest {

    @Test
    void testReadFromString() {
        ConfigurationStore s = new ConfigurationStore();
        assertNotNull(s);
    }

}