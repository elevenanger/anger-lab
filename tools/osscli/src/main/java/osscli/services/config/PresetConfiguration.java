package osscli.services.config;

import osscli.services.Oss;
import osscli.services.model.OssConfiguration;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Anger
 * created on 2023/2/26
 * sequoia aws 预置配置
 */
public enum PresetConfiguration {
    MAC_15_LOCAL(defaultSeqAwsCredentials().withEndPoint("http://192.168.48.129:8002/")),
    FL_DEV(defaultSeqAwsCredentials().withEndPoint("https://fldev.dgcb.com.cn:8080/"));

    PresetConfiguration(OssConfiguration ossConfiguration) {
        this.configuration = ossConfiguration;
    }
    private final OssConfiguration configuration;

    public OssConfiguration getConfiguration() {
        return configuration;
    }

    static PresetConfiguration fromValue(String value) {
        if (value == null || value.isEmpty())
            return null;

        String upperValue = value.toUpperCase();

        for (PresetConfiguration ossConfiguration : values()) {
            if (ossConfiguration.name().equals(upperValue))
                return ossConfiguration;
        }

        throw new IllegalArgumentException("没有对应的预置配置信息 ：" + value);
    }

    private static OssConfiguration defaultSeqAwsCredentials() {
        return new OssConfiguration()
            .withType(Oss.Type.AWS)
            .withAccessKey("ABCDEFGHIJKLMNOPQRST")
            .withSecreteKey("abcdefghijklmnopqrstuvwxyz0123456789ABCD");
    }

    public static String allStringForm() {
        return all().entrySet().stream()
            .map(entry -> entry.getKey() + " => " + entry.getValue().toString() + "\n")
            .reduce("", String::concat);
    }

    public static Map<Integer, OssConfiguration> all() {
        return EnumSet.allOf(PresetConfiguration.class).stream()
            .collect(Collectors.toMap(
                PresetConfiguration::ordinal,
                PresetConfiguration::getConfiguration
            ));
    }

}
