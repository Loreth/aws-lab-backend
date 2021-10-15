package pl.grupakpkpur.awslab.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.BucketDto;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class BucketService {
  private static final Region REGION = Region.US_EAST_1;
  private final S3Client s3 = S3Client.builder().region(REGION).build();

  public List<BucketDto> listBuckets() {
    return s3.listBuckets().buckets().stream()
        .map(bucket -> new BucketDto(bucket.name(), bucket.creationDate()))
        .collect(Collectors.toList());
  }
}
