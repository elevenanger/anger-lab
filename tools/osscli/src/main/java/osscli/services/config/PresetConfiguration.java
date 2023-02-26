package osscli.services.config;

import osscli.services.Oss;
import osscli.services.model.OssConfiguration;

/**
 * @author Anger
 * created on 2023/2/26
 * sequoia aws 预置配置
 */
public enum PresetConfiguration {
    MAC_15_LOCAL(new OssConfiguration()
            .withEndPoint("http://192.168.48.129:8002/")
            .withType(Oss.Type.AWS)
            .withAccessKey("ABCDEFGHIJKLMNOPQRST")
            .withSecreteKey("abcdefghijklmnopqrstuvwxyz0123456789ABCD")),
    FL_DEV(new OssConfiguration()
            .withType(Oss.Type.AWS)
            .withAccessKey("ABCDEFGHIJKLMNOPQRST")
            .withSecreteKey("abcdefghijklmnopqrstuvwxyz0123456789ABCD")
            .withEndPoint("https://fldev.dgcb.com.cn:8080/"));

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

}
