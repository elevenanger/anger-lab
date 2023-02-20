package osscli.services.aws;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import osscli.services.ClientFactory;
import osscli.services.ObjectService;
import osscli.services.model.object.GetObjectRequest;
import osscli.services.model.object.OssObject;
import osscli.services.model.object.PutObjectRequest;
import osscli.services.model.object.PutObjectResponse;

import static osscli.services.model.transform.RequestTransformers.seqAwsGetObjectRequestTransformer;
import static osscli.services.model.transform.RequestTransformers.seqAwsPutObjectRequestTransformer;
import static osscli.services.model.transform.ResponseTransformers.seqAwsPutObjectResponseTransformer;

/**
 * @author : anger
 * ObjectService 巨杉 Aws 实现
 */
public class SeqAwsObjectService implements ObjectService {
    @Override
    public PutObjectResponse putObject(PutObjectRequest request) {
        com.amazonaws.services.s3.model.PutObjectRequest putObjectRequest =
            seqAwsPutObjectRequestTransformer.transform(request);
        PutObjectResult result = ClientFactory.s3Client().putObject(putObjectRequest);
        return seqAwsPutObjectResponseTransformer.transform(result);
    }

    @Override
    public OssObject<S3Object> getObject(GetObjectRequest request) {
        com.amazonaws.services.s3.model.GetObjectRequest getObjectRequest =
            seqAwsGetObjectRequestTransformer.transform(request);
        S3Object object = ClientFactory.s3Client().getObject(getObjectRequest);

        OssObject<S3Object> ossObject = new OssObject<>();
        ossObject.setObject(object);
        return ossObject;
    }

}
