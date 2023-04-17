package osscli.services;

import osscli.services.aws.SeqAws;
import osscli.services.cos.COSService;
import osscli.services.model.OssConfiguration;

/**
 * @author : anger
 * Oss 工厂方法
 */
public class OssFactory {
    OssFactory() {}

    public static Oss getInstance(OssConfiguration configuration) {
        switch (configuration.getType()) {
            case AWS: return new SeqAws(configuration);
            case COS: return new COSService(configuration);
            default: return null;
        }
    }

}
