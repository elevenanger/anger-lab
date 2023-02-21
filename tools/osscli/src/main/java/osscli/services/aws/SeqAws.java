package osscli.services.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import osscli.services.AbstractOss;
import osscli.services.ClientFactory;
import osscli.services.model.ListObjectRequest;
import osscli.services.model.ListObjectResponse;
import osscli.services.model.GetObjectRequest;
import osscli.services.model.OssObject;
import osscli.services.model.PutObjectRequest;
import osscli.services.model.PutObjectResponse;

import static osscli.services.model.transform.RequestTransformers.seqAwsPutObjectRequestTransformer;
import static osscli.services.model.transform.RequestTransformers.seqAwsListObjectRequestTransformer;
import static osscli.services.model.transform.RequestTransformers.seqAwsGetObjectRequestTransformer;
import static osscli.services.model.transform.ResponseTransformers.seqAwsListObjectResponseTransformer;
import static osscli.services.model.transform.ResponseTransformers.seqAwsPutObjectResponseTransformer;

/**
 * @author : anger
 * Oss 服务巨杉数据库实现
 */
public class SeqAws extends AbstractOss {

    private final AmazonS3 s3 = ClientFactory.s3Client();

    @Override
    public synchronized void setEndpoint(String endpoint) {
        s3.setEndpoint(endpoint);
    }

    @Override
    public PutObjectResponse putObject(PutObjectRequest request) {
        com.amazonaws.services.s3.model.PutObjectRequest putObjectRequest =
            seqAwsPutObjectRequestTransformer.transform(request);
        PutObjectResult result = s3.putObject(putObjectRequest);
        return seqAwsPutObjectResponseTransformer.transform(result);
    }

    @Override
    public OssObject<S3Object> getObject(GetObjectRequest request) {
        com.amazonaws.services.s3.model.GetObjectRequest getObjectRequest =
            seqAwsGetObjectRequestTransformer.transform(request);
        S3Object object = s3.getObject(getObjectRequest);

        OssObject<S3Object> ossObject = new OssObject<>();
        ossObject.setObject(object);
        return ossObject;
    }

    @Override
    public ListObjectResponse listObjects(ListObjectRequest request) {
        ListObjectsV2Result result =
            s3.listObjectsV2(seqAwsListObjectRequestTransformer.transform(request));
        return seqAwsListObjectResponseTransformer.transform(result);
    }

}
