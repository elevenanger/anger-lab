package osscli.services.config;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import osscli.services.model.OssConfiguration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Anger
 * created on 2023/2/27
 */
class OssConfigurationStoreTest {

    @Test
    void testInitialize() throws IOException {
        Map<String, OssConfiguration> configurations = OssConfigurationStore.getAll();
        assertNotNull(configurations);
        configurations.entrySet().forEach(System.out::println);
    }

    @Test
    void testGetAll() {
        String conf = OssConfigurationStore.getAllAsString();
        assertNotNull(conf);
        System.out.println(conf);
    }

    @Test
    void testGetOne() {
        OssConfiguration configuration = OssConfigurationStore.getOne("fl_dev");
        assertNotNull(configuration);
        System.out.println(configuration);
    }

    @Test
    void dumpTest() throws IOException {
        Yaml yaml = new Yaml(new Constructor(Map.class));

        Map<String, OssConfiguration> ossConfigurationMap = new HashMap<>();
        ossConfigurationMap.put("fl_dev", PresetConfiguration.FL_DEV.getConfiguration());
        ossConfigurationMap.put("mac_15", PresetConfiguration.MAC_15_LOCAL.getConfiguration());

        yaml.dump(ossConfigurationMap, new FileWriter("/Users/liuanglin/Desktop/dump.yml"));
    }

    @Test
    void dumpConfTest() {
        OssConfigurationStore.dumpConfig("/Users/liuanglin/Desktop/dump.yml");
    }

}