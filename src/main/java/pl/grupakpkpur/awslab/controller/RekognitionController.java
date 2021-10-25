package pl.grupakpkpur.awslab.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.grupakpkpur.awslab.model.rekognition.ImageLabel;
import pl.grupakpkpur.awslab.service.RekognitionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.rekognition}")
public class RekognitionController {
  private final RekognitionService rekognitionService;

  @PostMapping("${rest.mapping.rekognition.labelling}")
  public List<ImageLabel> detectLabels(@RequestPart MultipartFile imageFile) {
    return rekognitionService.detectLabels(imageFile);
  }
}
