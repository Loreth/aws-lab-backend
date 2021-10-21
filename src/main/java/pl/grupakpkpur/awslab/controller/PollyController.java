package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pl.grupakpkpur.awslab.model.polly.GenerateSpeechRequest;
import pl.grupakpkpur.awslab.model.polly.VoiceResponse;
import pl.grupakpkpur.awslab.service.PollyService;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("${rest.mapping.polly}")
public class PollyController {

	private final PollyService pollyService;

	@GetMapping("/languages")
	@ResponseBody
	public List<String> getLanguages() {
		return pollyService.getLanguages();
	}

	@GetMapping("/voices")
	@ResponseBody
	public List<VoiceResponse> getVoices(@RequestParam("languageCode") String languageCode) {
		return pollyService.getVoices(languageCode);
	}

	@PutMapping(value = "/generate", produces = {"audio/mpeg"})
	public StreamingResponseBody generateSpeech(@RequestBody GenerateSpeechRequest request) {
		return outputStream -> outputStream.write(pollyService.generateSpeech(request));
	}
}
