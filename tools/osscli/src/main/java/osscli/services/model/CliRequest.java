package osscli.services.model;

import osscli.services.Oss;

import java.util.Date;

/**
 * @author : anger
 * 客户端请求父类
 */
public class CliRequest {

    private final Date requestTime = new Date();
    private Oss.OSSType ossType;

    public Oss.OSSType getOssType() {
        return ossType;
    }

    public void setOssType(Oss.OSSType ossType) {
        this.ossType = ossType;
    }

    public Date getRequestTime() {
        return requestTime;
    }

}
