package pl.grupakpkpur.awslab.service;

import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.polly.GenerateSpeechRequest;
import pl.grupakpkpur.awslab.model.polly.VoiceResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollyService {

	private static final Region REGION = Region.US_EAST_1;
	private static final PollyClient POLLY_CLIENT = PollyClient.builder().region(REGION).build();

	public List<String> getLanguages() {
		return LanguageCode.knownValues().stream()
				.map(LanguageCode::toString)
				.collect(Collectors.toList());
	}

	public byte[] generateSpeech(GenerateSpeechRequest request) throws IOException {
		return POLLY_CLIENT.synthesizeSpeech(
				SynthesizeSpeechRequest.builder()
						.text(request.text())
						.voiceId(request.voiceId())
						.outputFormat(OutputFormat.MP3).build()
		).readAllBytes();
	}

	public List<VoiceResponse> getVoices(String languageCode) {
		return POLLY_CLIENT.describeVoices(
				DescribeVoicesRequest.builder()
						.engine(Engine.STANDARD)
						.languageCode(languageCode)
						.build()
		).voices().stream()
				.map(voice -> new VoiceResponse(voice.idAsString(), voice.name(), voice.genderAsString(), voice.languageCodeAsString()))
				.collect(Collectors.toList());
	}
}
