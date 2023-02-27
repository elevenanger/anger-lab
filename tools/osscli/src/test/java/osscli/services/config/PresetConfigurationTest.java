package osscli.services.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import osscli.services.model.OssConfiguration;

import java.util.Map;

/**
 * @author Anger
 * created on 2023/2/26
 */
class PresetConfigurationTest {

    @Test
    void testAll() {
        Map<Integer, OssConfiguration> map = PresetConfiguration.all();
        Assertions.assertNotNull(map);
        System.out.println(map);
    }

}