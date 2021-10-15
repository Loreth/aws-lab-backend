package pl.grupakpkpur.awslab.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.grupakpkpur.awslab.model.S3ObjectDto;
import pl.grupakpkpur.awslab.service.BucketService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.s3object}")
public class S3ObjectController {
  private final BucketService bucketService;

  @GetMapping()
  public List<S3ObjectDto> getObjectsInBucket(@RequestParam String bucketName) {
    return bucketService.listFilesInBucket(bucketName);
  }
}
