package pl.grupakpkpur.awslab.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.grupakpkpur.awslab.model.BucketDto;
import pl.grupakpkpur.awslab.model.S3ObjectDto;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

@Service
public class BucketService {
  private static final Region REGION = Region.US_EAST_1;
  private final S3Client s3 = S3Client.builder().region(REGION).build();

  public List<BucketDto> listBuckets() {
    return s3.listBuckets().buckets().stream()
        .map(bucket -> new BucketDto(bucket.name(), bucket.creationDate()))
        .toList();
  }

  public List<S3ObjectDto> listFilesInBucket(String bucketName) {
    return s3.listObjectsV2(builder -> builder.bucket(bucketName)).contents().stream()
        .map(s3Object -> new S3ObjectDto(s3Object.key(), s3Object.size(), s3Object.lastModified()))
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
}
