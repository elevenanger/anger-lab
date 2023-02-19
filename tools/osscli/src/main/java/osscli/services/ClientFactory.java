package osscli.services;

import com.amazonaws.services.s3.AmazonS3;
import osscli.services.aws.SeqAWSClient;

/**
 * @author : anger
 * client 工厂方法
 */
public class ClientFactory {

    public static AmazonS3 s3Client() {
        return new SeqAWSClient().getClient();
    }

}
