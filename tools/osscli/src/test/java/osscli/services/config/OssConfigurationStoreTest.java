package osscli.services.config;

import org.junit.jupiter.api.Test;
import osscli.services.model.OssConfiguration;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Anger
 * created on 2023/2/27
 */
class OssConfigurationStoreTest {

    @Test
    void testInitialize() throws IOException {
        List<OssConfiguration> configurations = OssConfigurationStore.getAll();
        assertNotNull(configurations);
        configurations.forEach(System.out::println);
    }

    @Test
    void testGetAll() {
        String conf = OssConfigurationStore.getAllAsString();
        assertNotNull(conf);
        System.out.println(conf);
    }

    @Test
    void testGetOne() {
        OssConfiguration configuration = OssConfigurationStore.getOne(1);
        assertNotNull(configuration);
        System.out.println(configuration);
    }

}