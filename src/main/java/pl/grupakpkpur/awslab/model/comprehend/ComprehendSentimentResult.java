package pl.grupakpkpur.awslab.model.comprehend;

import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;

public record ComprehendSentimentResult(Float neutral, Float mixed, Float negative, Float positive) {

	public ComprehendSentimentResult(DetectSentimentResponse response) {
		this(response.sentimentScore().neutral(), response.sentimentScore().mixed(), response.sentimentScore().negative(), response.sentimentScore().positive());
	}
}
