package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.grupakpkpur.awslab.model.logging.LogEntryRequest;
import pl.grupakpkpur.awslab.service.LoggingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("${rest.mapping.logging}")
public class LoggingController {

	private final LoggingService loggingService;

	@PostMapping("/log-entry")
	public void log(@RequestBody LogEntryRequest request) {
		this.loggingService.logEntry(request);
	}
}
