package osscli.client;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AWSClient {

    private final AmazonS3 s3;

    @Autowired
    public AWSClient() {
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
    }

    public AmazonS3 getS3Client() {
        return s3;
    }
}
