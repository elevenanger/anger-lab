package osscli.services.config;

import cn.anger.util.cipher.sm.SM4Util;
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
import java.util.Collections;
import java.util.HashMap;
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

    private static final AtomicInteger index = new AtomicInteger(0);

    public static void addOne(OssConfiguration configuration) {
        storage.setConfigurations(
                Collections.singletonMap(
                        "custom" + index.incrementAndGet(), configuration));
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

    public static OssConfiguration defaultConfiguration() {
        return storage.getDefaultConfiguration();
    }

    private static final class ConfigStorage {
        private static final String OSS_CONFIG = "oss-config.yml";
        private static final String KEY = "fe6f915f273336a487548db7b364744c";
        private static final Yaml YAML = getYaml();
        private final Map<String, OssConfiguration> configurations = new HashMap<>();
        private String defaultConfiguration;

        private static Yaml getYaml() {
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
            ConfigStorage configStorage = YAML.load(stream);
            this.configurations.putAll(configStorage.getConfigurations());
            if (configStorage.defaultConfiguration != null)
                setDefaultConfiguration(configStorage.defaultConfiguration);
        }

        public void dump(String path) {
            try (FileWriter writer = new FileWriter(path)){
                YAML.dump(this, writer);
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

        public OssConfiguration getDefaultConfiguration() {
            return getConfiguration(defaultConfiguration);
        }

        public OssConfiguration getConfiguration(String key) {
            return decryptConfiguration(getConfigurations().get(key));
        }

        private OssConfiguration decryptConfiguration(final OssConfiguration originConfiguration) {
            OssConfiguration configuration = new OssConfiguration();
            String accessKey;
            String secreteKey;

            try {
                accessKey = SM4Util.decrypt(KEY, originConfiguration.getAccessKey());
                secreteKey = SM4Util.decrypt(KEY, originConfiguration.getSecreteKey());
            } catch (Exception e) {
                accessKey = originConfiguration.getAccessKey();
                secreteKey = originConfiguration.getSecreteKey();
            }

            configuration.setAccessKey(accessKey);
            configuration.setSecreteKey(secreteKey);
            configuration.setType(originConfiguration.getType());
            configuration.setEndPoint(originConfiguration.getEndPoint());

            return configuration;
        }

        public void setDefaultConfiguration(String defaultConfiguration) {
            this.defaultConfiguration = defaultConfiguration;
        }

        @Override
        public String toString() {
            return "ConfigStorage:\n" +
                        getConfigurations().entrySet().stream()
                            .map(entry -> entry.getKey() + " => " + entry.getValue())
                            .collect(Collectors.joining("\n"));
        }
    }
}