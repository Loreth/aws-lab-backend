package pl.grupakpkpur.awslab.service;

import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendSentimentRequest;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendSentimentResult;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentRequest;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;

@Service
public class ComprehendService {

	private static final Region REGION = Region.US_EAST_1;
	private static final ComprehendClient COMPREHEND_CLIENT = ComprehendClient.builder().region(REGION).build();

	public ComprehendSentimentResult detectSentiment(ComprehendSentimentRequest request) {
		DetectSentimentRequest detectSentimentRequest = DetectSentimentRequest.builder().text(request.text()).languageCode("en").build();

		DetectSentimentResponse detectSentimentResult = COMPREHEND_CLIENT.detectSentiment(detectSentimentRequest);

		return new ComprehendSentimentResult(detectSentimentResult);
	}
}
