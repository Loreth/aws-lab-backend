package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.grupakpkpur.awslab.model.translation.TranslationRequest;
import pl.grupakpkpur.awslab.model.translation.TranslationResponse;
import pl.grupakpkpur.awslab.service.TranslationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.translation}")
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping("/translate")
    @ResponseBody
    public TranslationResponse detectSentiment(@RequestBody TranslationRequest request) {
        return translationService.translate(request);
    }
}
