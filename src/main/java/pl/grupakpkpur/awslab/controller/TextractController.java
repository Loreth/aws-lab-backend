package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.grupakpkpur.awslab.model.textract.DetectedText;
import pl.grupakpkpur.awslab.service.TextractService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.textract}")
public class TextractController {
  private final TextractService textractService;

  @PostMapping
  public DetectedText detectDocumentText(@RequestPart MultipartFile file) {
    return textractService.detectDocumentText(file);
  }
}
