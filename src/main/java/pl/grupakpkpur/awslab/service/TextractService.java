package pl.grupakpkpur.awslab.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.grupakpkpur.awslab.model.textract.DetectedText;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.Block;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextRequest;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextResponse;
import software.amazon.awssdk.services.textract.model.Document;

@Service
@RequiredArgsConstructor
public class TextractService {
  private static final Region REGION = Region.US_EAST_1;
  private final TextractClient textractClient = TextractClient.builder().region(REGION).build();

  public DetectedText detectDocumentText(MultipartFile documentFile) {
    Document document;
    try {
      document = Document.builder().bytes(SdkBytes.fromByteArray(documentFile.getBytes())).build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    DetectDocumentTextRequest detectDocumentTextRequest =
        DetectDocumentTextRequest.builder().document(document).build();

    DetectDocumentTextResponse textResponse =
        textractClient.detectDocumentText(detectDocumentTextRequest);

    List<Block> documentBlocks = textResponse.blocks();

    return new DetectedText(documentBlocks.stream().map(Block::text).skip(1).collect(Collectors.joining(" ")));
  }
}
