package osscli.services.client;

import com.amazonaws.services.s3.AmazonS3;

/**
 * @author : anger
 * client 工厂方法
 */
public class ClientFactory {

    private ClientFactory() {}

    public static AmazonS3 s3Client() {
        return new SeqAWSClient().getClient();
    }

}
