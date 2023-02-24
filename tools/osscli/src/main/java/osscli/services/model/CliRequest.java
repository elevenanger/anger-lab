package osscli.services.model;

import osscli.services.Oss;

import java.util.Date;

/**
 * @author : anger
 * 客户端请求父类
 */
public class CliRequest {

    private final Date requestTime = new Date();
    private Oss.Type type;

    public Oss.Type getOssType() {
        return type;
    }

    public void setOssType(Oss.Type type) {
        this.type = type;
    }

    public Date getRequestTime() {
        return requestTime;
    }

}
