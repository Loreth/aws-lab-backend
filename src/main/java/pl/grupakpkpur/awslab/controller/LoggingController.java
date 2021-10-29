package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
