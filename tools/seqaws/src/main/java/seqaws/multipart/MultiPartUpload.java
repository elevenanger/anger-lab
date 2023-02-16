package seqaws.multipart;

import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seqaws.client.AWSClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MultiPartUpload {
    @Autowired
    private AWSClient s3Client;

    public void multiPartUploadObject(String bucketName, String objectName, File file) {
        log.info("Multi part upload  enter");
        long maxSize = 5 * 1024 * 1024;

        List<PartETag> partETags = new ArrayList<>();
        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, objectName);
        InitiateMultipartUploadResult result = s3Client.getS3Client().initiateMultipartUpload(initRequest);
        String uploadId = result.getUploadId();

        long filePosition = 0;
        long fileLength = file.length();
        for (int i = 1; filePosition < fileLength; i++) {
            long partSize = Math.min(maxSize, (fileLength - filePosition));
            UploadPartRequest partRequest = new UploadPartRequest()
                    .withFile(file)
                    .withFileOffset(filePosition)
                    .withPartNumber(i)
                    .withPartSize(partSize)
                    .withBucketName(bucketName)
                    .withKey(objectName)
                    .withUploadId(uploadId);
            UploadPartResult uploadPartResult = s3Client.getS3Client().uploadPart(partRequest);
            partETags.add(uploadPartResult.getPartETag());
            filePosition += partSize;
        }

        ListPartsRequest listPartsRequest =
                new ListPartsRequest(bucketName, objectName, uploadId);
        PartListing listResult = s3Client.getS3Client().listParts(listPartsRequest);
        int size = listResult.getParts().size();
        for (int i = 0; i < size; i++) {
            log.info("i:" + i +
                    ", part number:" + listResult.getParts().get(i).getPartNumber() +
                    ", part size:" + listResult.getParts().get(i).getSize() +
                    ", part eTag:" + listResult.getParts().get(i).getETag());
        }

        CompleteMultipartUploadRequest completeRequest = new CompleteMultipartUploadRequest()
                .withBucketName(bucketName)
                .withKey(objectName)
                .withUploadId(uploadId)
                .withPartETags(partETags);
        CompleteMultipartUploadResult result1 = s3Client.getS3Client().completeMultipartUpload(completeRequest);
        log.info("complete eTag:" + result1.getETag());
    }
}
