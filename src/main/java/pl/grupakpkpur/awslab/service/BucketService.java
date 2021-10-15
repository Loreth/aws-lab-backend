package pl.grupakpkpur.awslab.service;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.BucketDto;
import pl.grupakpkpur.awslab.model.S3ObjectDto;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;

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
    ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).build();
    return s3.listObjectsV2(request).contents().stream()
        .map(s3Object -> new S3ObjectDto(s3Object.key(), s3Object.size(), s3Object.lastModified()))
        .toList();
  }
}
