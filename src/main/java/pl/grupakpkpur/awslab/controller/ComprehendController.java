package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendSentimentRequest;
import pl.grupakpkpur.awslab.model.comprehend.ComprehendSentimentResult;
import pl.grupakpkpur.awslab.service.ComprehendService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.comprehend}")
public class ComprehendController {

	private final ComprehendService comprehendService;

	@PostMapping("/detect")
	@ResponseBody
	public ComprehendSentimentResult detectSentiment(@RequestBody ComprehendSentimentRequest request) {
		return comprehendService.detectSentiment(request);
	}
}
