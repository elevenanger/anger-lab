package osscli.services.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import osscli.services.model.ClientConfiguration;

/**
 * @author : anger
 * 初始化 client 类
 */
public class SeqAWSClient implements Client<AmazonS3> {
    private static final AmazonS3 s3 = AmazonS3Holder.s3;

    private static class AmazonS3Holder {
        private static AmazonS3 s3 = s3Initializer();

        private static AmazonS3 s3Initializer() {
            String endPoint = "https://fldev.dgcb.com.cn:8080";
            String accessKey = "ABCDEFGHIJKLMNOPQRST";
            String secreteKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCD";

            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secreteKey);
            AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
            AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration(endPoint, null);

            s3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withPathStyleAccessEnabled(true)
                .withCredentials(provider)
                .build();

            return s3;
        }
    }

    @Override
    public AmazonS3 getClient() {
        return s3;
    }

    @Override
    public synchronized void clientSetUp(ClientConfiguration configuration) {
        s3.setEndpoint(configuration.getEndPoint());
    }
}
