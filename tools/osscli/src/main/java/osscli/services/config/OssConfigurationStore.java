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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        storage.setConfigurations(Collections.singletonList(configuration));
    }

    public static OssConfiguration getOne(int index) {
        return storage.getConfiguration(index);
    }

    public static List<OssConfiguration> getAll() {
        return storage.getConfigurations();
    }

    public static Map<Integer, OssConfiguration> getAllAsMap() {
        return storage.toMap();
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
        private final List<OssConfiguration> configurations = new ArrayList<>();

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
            configurations.addAll(PresetConfiguration.presetConfigurations());
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
            this.configurations.addAll(configStorage.getConfigurations());
        }

        public void dump(String path) {
            try (FileWriter writer = new FileWriter(path)){
                yaml.dump(this, writer);
            } catch (IOException e) {
                throw new OssBaseException(e);
            }
        }

        public List<OssConfiguration> getConfigurations() {
            return Collections.unmodifiableList(configurations);
        }

        public void setConfigurations(List<OssConfiguration> configurations) {
            this.configurations.addAll(configurations);
        }
        public OssConfiguration getConfiguration(int index) {
            return toMap().get(index);
        }

        public Map<Integer, OssConfiguration> toMap() {
            final AtomicInteger index = new AtomicInteger(0);
            return getConfigurations().stream()
                .collect(Collectors.toMap(
                    conf -> index.incrementAndGet(),
                    conf -> conf
                ));
        }

        @Override
        public String toString() {
            return "ConfigStorage:\n" +
                toMap().entrySet().stream()
                    .map(entry -> entry.getKey() + " => " + entry.getValue() + "\n")
                    .reduce("", String::concat);
        }
    }
}