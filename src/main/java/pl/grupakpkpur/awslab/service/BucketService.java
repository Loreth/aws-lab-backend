package pl.grupakpkpur.awslab.service;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.grupakpkpur.awslab.model.s3.BucketDto;
import pl.grupakpkpur.awslab.model.s3.S3ObjectDto;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BucketService {
  private static final Region REGION = Region.US_EAST_1;
  private final S3Client s3 = S3Client.builder().region(REGION).build();
  private final S3Presigner s3Presigner;

  public List<BucketDto> listBuckets() {
    return s3.listBuckets().buckets().stream()
        .map(bucket -> new BucketDto(bucket.name(), bucket.creationDate()))
        .toList();
  }

  public List<S3ObjectDto> listFilesInBucket(String bucketName) {
    return s3.listObjectsV2(builder -> builder.bucket(bucketName)).contents().stream()
        .map(
            s3Object ->
                new S3ObjectDto(
                    s3Object.key(),
                    s3Object.size(),
                    s3Object.lastModified(),
                    createPresignedS3ObjectDownloadUrl(bucketName, s3Object.key())))
        .toList();
  }

  public void uploadFilesToBucket(String bucketName, List<MultipartFile> files) {
    for (MultipartFile f:files) {
    PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(f.getOriginalFilename())
            .build();

      try {
        s3.putObject(objectRequest, RequestBody.fromBytes(f.getBytes()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private URL createPresignedS3ObjectDownloadUrl(String bucketName, String objectKey) {
    return s3Presigner
        .presignGetObject(
            presignRequestBuilder ->
                presignRequestBuilder
                    .signatureDuration(Duration.ofHours(2))
                    .getObjectRequest(
                        objectRequestBuilder ->
                            objectRequestBuilder
                                .bucket(bucketName)
                                .key(objectKey)
                                .responseContentDisposition(
                                    "attachment; filename=\"" + objectKey + "\"")))
        .url();
  }
}
