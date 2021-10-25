package pl.grupakpkpur.awslab.model.translation;

public record TranslationRequest(String textToTranslate, String sourceLanguageCode, String targetLanguageCode) {
}
