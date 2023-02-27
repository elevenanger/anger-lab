package osscli.services.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import osscli.exception.OssBaseException;
import osscli.services.model.OssConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * @author Anger
 * created on 2023/2/27
 * Oss 配置信息仓库
 * 分别从 {@link PresetConfiguration} 和配置文件中读取配置信息
 */
public class ConfigurationStore {
    private static final Yaml yaml = new Yaml(new Constructor(ConfigurationStore.class));
    private static final List<OssConfiguration> configurations = PresetConfiguration.presetConfigurations();

    private static void initialize() {
        configurations.addAll(PresetConfiguration.presetConfigurations());

    }

    public static List<OssConfiguration> loadConfig(String path) {
        try (InputStream stream = Files.newInputStream(Paths.get(path))){
            return ((ConfigurationStore) yaml.load(stream)).getConfigurations();
        } catch (IOException e) {
            throw new OssBaseException(e);
        }
    }

    public List<OssConfiguration> getConfigurations() {
        return Collections.unmodifiableList(configurations);
    }

    public void setConfigurations(List<OssConfiguration> configurations) {
        ConfigurationStore.configurations.addAll(configurations);
    }

}
