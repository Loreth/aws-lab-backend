package pl.grupakpkpur.awslab.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.translation.TranslationRequest;
import pl.grupakpkpur.awslab.model.translation.TranslationResponse;

@Service
public class TranslationService {
    private static final Regions REGION = Regions.US_EAST_1;

    AmazonTranslate translate = AmazonTranslateClient.builder()
            .withRegion(REGION)
            .build();

    public TranslationResponse translate(TranslationRequest request){
        System.out.println(request);
        TranslateTextRequest tRequest = new TranslateTextRequest()
                .withText(request.textToTranslate())
                .withSourceLanguageCode(request.sourceLanguageCode())
                .withTargetLanguageCode(request.targetLanguageCode());
        TranslateTextResult result = translate.translateText(tRequest);
        String translated = result.getTranslatedText();
        return new TranslationResponse(translated);
    }

}
