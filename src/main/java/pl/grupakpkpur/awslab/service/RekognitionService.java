package pl.grupakpkpur.awslab.service;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.grupakpkpur.awslab.model.rekognition.ImageLabel;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;

@Service
@RequiredArgsConstructor
public class RekognitionService {
  private static final Region REGION = Region.US_EAST_1;
  private final RekognitionClient rekClient = RekognitionClient.builder().region(REGION).build();

  public List<ImageLabel> detectLabels(MultipartFile imageFile) {
    Image sourceImage;
    try {
      sourceImage = Image.builder().bytes(SdkBytes.fromByteArray(imageFile.getBytes())).build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    DetectLabelsRequest detectLabelsRequest =
        DetectLabelsRequest.builder().image(sourceImage).maxLabels(10).build();
    DetectLabelsResponse labelsResponse = rekClient.detectLabels(detectLabelsRequest);

    return labelsResponse.labels().stream()
        .map(label -> new ImageLabel(label.name(), label.confidence()))
        .toList();
  }
}
