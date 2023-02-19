package osscli.services.model;

import osscli.base.OSSType;

import java.util.Date;

/**
 * @author : anger
 * 客户端请求父类
 */
public class CliRequest {

    private final Date requestTime = new Date();
    private OSSType ossType;

    public OSSType getOssProtocol() {
        return ossType;
    }

    public void setOssProtocol(OSSType ossType) {
        this.ossType = ossType;
    }

    public Date getRequestTime() {
        return requestTime;
    }

}
