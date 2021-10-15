package pl.grupakpkpur.awslab.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.grupakpkpur.awslab.model.BucketDto;
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
}
