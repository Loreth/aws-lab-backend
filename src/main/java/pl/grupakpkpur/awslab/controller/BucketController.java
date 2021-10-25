package pl.grupakpkpur.awslab.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.grupakpkpur.awslab.model.s3.BucketDto;
import pl.grupakpkpur.awslab.model.s3.S3ObjectDto;
import pl.grupakpkpur.awslab.service.BucketService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.bucket}")
public class BucketController {
  private final BucketService bucketService;

  @GetMapping
  public List<BucketDto> getBuckets() {
    return bucketService.listBuckets();
  }

  @GetMapping("/{bucketName}")
  public List<S3ObjectDto> getObjectsInBucket(@PathVariable String bucketName) {
    return bucketService.listFilesInBucket(bucketName);
  }

  @PostMapping("/{bucketName}")
  public void uploadFiles(@PathVariable String bucketName, @RequestPart("fileList") List<MultipartFile> files){
    bucketService.uploadFilesToBucket(bucketName, files);
  }
}
