package osscli.services.config;

import org.junit.jupiter.api.Test;

/**
 * @author Anger
 * created on 2023/2/26
 */
class PresetConfigurationTest {

    @Test
    void testConfig() {
        System.out.println(PresetConfiguration.MAC_15_LOCAL.getConfiguration());
    }

}