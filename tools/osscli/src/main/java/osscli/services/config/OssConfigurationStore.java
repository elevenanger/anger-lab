package osscli.services.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import osscli.exception.OssBaseException;
import osscli.services.model.OssConfiguration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Anger
 * created on 2023/2/27
 * Oss 配置信息仓库
 * 分别从 {@link PresetConfiguration} 和配置文件中读取配置信息
 */
public class OssConfigurationStore {
    private OssConfigurationStore() {
        throw new OssBaseException("不正确的实例化方式");
    }
    private static final ConfigStorage storage = new ConfigStorage().initialize();

    public static void addOne(OssConfiguration configuration) {
        storage.setConfigurations(
                Collections.singletonMap(
                        "custom", configuration));
    }

    public static OssConfiguration getOne(String key) {
        return storage.getConfiguration(key);
    }

    public static Map<String, OssConfiguration> getAll() {
        return storage.getConfigurations();
    }

    public static String getAllAsString() {
        return storage.toString();
    }

    public static void loadConfig(String path) {
        storage.loadConfig(path);
    }

    public static void dumpConfig(String path) {
        storage.dump(path);
    }

    private static final class ConfigStorage {
        private static final String OSS_CONFIG = "oss-config.yml";
        private static final Yaml yaml = buildYaml();
        private final Map<String, OssConfiguration> configurations = new HashMap<>();

        private static Yaml buildYaml() {
            Yaml yaml = new Yaml(new Constructor(ConfigStorage.class));
            yaml.setBeanAccess(BeanAccess.FIELD);
            return yaml;
        }

        public ConfigStorage initialize() {
            loadFromPreset();
            loadFromSystem();
            return this;
        }

        private void loadFromPreset() {
            configurations.putAll(PresetConfiguration.presetConfigurations());
        }

        private void loadFromSystem() {
            loadConfig(getClass().getClassLoader().getResourceAsStream(OSS_CONFIG));
        }

        public void loadConfig(String path) {
            try (InputStream stream = Files.newInputStream(Paths.get(path))){
                loadConfig(stream);
            } catch (IOException e) {
                throw new OssBaseException(e);
            }
        }

        public void loadConfig(InputStream stream) {
            ConfigStorage configStorage = yaml.load(stream);
            this.configurations.putAll(configStorage.getConfigurations());
        }

        public void dump(String path) {
            try (FileWriter writer = new FileWriter(path)){
                yaml.dump(this, writer);
            } catch (IOException e) {
                throw new OssBaseException(e);
            }
        }

        public Map<String, OssConfiguration> getConfigurations() {
            return Collections.unmodifiableMap(configurations);
        }

        public void setConfigurations(Map<String, OssConfiguration> configurations) {
            this.configurations.putAll(configurations);
        }
        public OssConfiguration getConfiguration(String key) {
            return getConfigurations().get(key);
        }

        @Override
        public String toString() {
            return "ConfigStorage:\n" +
                getConfigurations().entrySet().stream()
                    .map(entry -> entry.getKey() + " => " + entry.getValue() + "\n")
                    .reduce("", String::concat);
        }
    }
}