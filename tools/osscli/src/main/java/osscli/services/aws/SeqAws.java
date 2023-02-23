package osscli.services.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import osscli.exception.LaunderAwsExceptions;
import osscli.services.AbstractOss;
import osscli.services.client.ClientFactory;
import osscli.services.model.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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
    public PutObjectResponse putObject(String bucket, String fileName, File file) {
        return putObject(new PutObjectRequest(bucket, fileName, file));
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
    public DownloadObjectResponse downloadObject(final DownloadObjectRequest request) {
        System.out.println(Thread.currentThread() + " downloading file " + request.getKey());

        S3Object result = s3.getObject(request.getBucket(), request.getKey());
        File localFile = Paths.get(request.getDownloadPath(), request.getKey()).toFile();

        long size = 0;

        try (BufferedInputStream s3is = new BufferedInputStream(result.getObjectContent(), BUFFER_SIZE);
                FileOutputStream fos = new FileOutputStream(localFile)) {
            FileChannel fc = fos.getChannel();
            byte[] readBuf = new byte[BUFFER_SIZE];
            ByteBuffer buffer;
            while (s3is.read(readBuf) > 0) {
                buffer = ByteBuffer.wrap(readBuf);
                fc.write(buffer);
                size += readBuf.length;
            }
        } catch (Exception e) {
            LaunderAwsExceptions.launder(e);
        }

        return new DownloadObjectResponse(request.getBucket(), request.getKey(), localFile.getAbsolutePath(), size);
    }

    @Override
    public DownloadObjectResponse downloadObject(String bucket, String key, String downloadPath) {
        return downloadObject(new DownloadObjectRequest(bucket, key, downloadPath));
    }

    @Override
    public OssObject<S3Object> getObject(String bucket, String key) {
        return getObject(new GetObjectRequest(bucket, key));
    }



    @Override
    public ListObjectsResponse listObjects(ListObjectsRequest request) {
        ListObjectsV2Result result =
            s3.listObjectsV2(seqAwsListObjectRequestTransformer.transform(request));
        return seqAwsListObjectResponseTransformer.transform(result);
    }

    @Override
    public ListObjectsResponse listObjects(String bucket, String prefix) {
        return listObjects(new ListObjectsRequest(bucket, prefix));
    }

    @Override
    public ListAllObjectsResponse listAllObjects(ListAllObjectRequest request) {
        List<ObjectSummary> objectSummaryList = new ArrayList<>();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(request.getBucket(), request.getPrefix());

        final Supplier<List<ObjectSummary>> supplier = () ->
            listObjects(listObjectsRequest).getObjectSummaries();

        List<ObjectSummary> summaries;

        while (objectSummaryList.addAll(summaries = supplier.get())) {
            listObjectsRequest
                .setStartAfter(summaries.get(summaries.size() - 1).getKey());
        }

        ListAllObjectsResponse response = new ListAllObjectsResponse();
        response.setObjectSummaryList(objectSummaryList);

        return response;
    }

    @Override
    public ListAllObjectsResponse listAllObjects(String bucket, String prefix) {
        return listAllObjects(new ListAllObjectRequest(bucket, prefix));
    }
}