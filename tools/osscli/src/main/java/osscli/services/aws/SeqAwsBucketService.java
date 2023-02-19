package osscli.services.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import osscli.services.BucketService;
import osscli.services.ClientFactory;
import osscli.services.model.bucket.ListObjectRequest;
import osscli.services.model.bucket.ListObjectResponse;

import static osscli.services.model.transform.ResponseTransformers.seqAwsListObjectResponseTransformer;
import static osscli.services.model.transform.RequestTransformers.seqAwsListObjectRequestTransformer;

/**
 * @author : anger
 * BucketService 巨杉 AWS 实现
 */
public class SeqAwsBucketService implements BucketService {

    @Override
    public ListObjectResponse listObjects(ListObjectRequest request) {
        final AmazonS3 s3 = ClientFactory.s3Client();
        ListObjectsV2Result result =
            s3.listObjectsV2(seqAwsListObjectRequestTransformer.transform(request));
        return seqAwsListObjectResponseTransformer.transform(result);
    }

    @Override
    public ListObjectResponse listAllObjects(ListObjectRequest request) {
        return null;
    }
}
