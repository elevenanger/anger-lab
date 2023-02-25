package osscli.services.model;

import osscli.services.Oss;

/**
 * @author : anger
 * 对象存储客户端配置信息
 */
public class OssConfiguration {

    Oss.Type type;

    /**
     * 地址
     */
    private String endPoint;

    public Oss.Type getType() {
        return type;
    }

    public OssConfiguration withType(Oss.Type type) {
        this.type = type;
        return this;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public OssConfiguration withEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }
}
