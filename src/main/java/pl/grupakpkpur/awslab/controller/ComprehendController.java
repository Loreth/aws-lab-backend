package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendLanguageResult;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendTextRequest;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendSentimentResult;
import pl.grupakpkpur.awslab.service.ComprehendService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.comprehend}")
public class ComprehendController {

	private final ComprehendService comprehendService;

	@PostMapping("/detectSentiment")
	@ResponseBody
	public ComprehendSentimentResult detectSentiment(@RequestBody ComprehendTextRequest request) {
		return comprehendService.detectSentiment(request);
	}

	@PostMapping("/detectLanguage")
	@ResponseBody
	public ComprehendLanguageResult detectLanguage(@RequestBody ComprehendTextRequest request) {
		return comprehendService.detectLanguage(request);
	}
}
