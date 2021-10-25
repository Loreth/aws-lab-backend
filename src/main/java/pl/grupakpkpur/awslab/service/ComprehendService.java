package pl.grupakpkpur.awslab.service;

import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendLanguageResult;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendTextRequest;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendSentimentResult;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ComprehendService {

    private static final Region REGION = Region.US_EAST_1;
    private static final ComprehendClient COMPREHEND_CLIENT = ComprehendClient.builder().region(REGION).build();

    public ComprehendSentimentResult detectSentiment(ComprehendTextRequest request) {
        DetectSentimentRequest detectSentimentRequest = DetectSentimentRequest.builder().text(request.text()).languageCode("en").build();

        DetectSentimentResponse detectSentimentResult = COMPREHEND_CLIENT.detectSentiment(detectSentimentRequest);

        return new ComprehendSentimentResult(detectSentimentResult);
    }

    public ComprehendLanguageResult detectLanguage(ComprehendTextRequest request) {
        DetectDominantLanguageRequest detectDominantLanguageRequest = DetectDominantLanguageRequest.builder().text(request.text()).build();
        DetectDominantLanguageResponse detectDominantLanguageResponse = COMPREHEND_CLIENT.detectDominantLanguage(detectDominantLanguageRequest);
        List<DominantLanguage> detectedLanguages = new ArrayList(detectDominantLanguageResponse.languages());
        detectedLanguages.sort(Comparator.comparing(DominantLanguage::score));
        DominantLanguage mostPossibleLanguage = detectedLanguages.get(0);
        return new ComprehendLanguageResult(mostPossibleLanguage.languageCode(), mostPossibleLanguage.score());
    }
}
