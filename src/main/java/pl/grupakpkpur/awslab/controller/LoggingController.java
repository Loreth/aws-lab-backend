package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.grupakpkpur.awslab.model.authentication.User;
import pl.grupakpkpur.awslab.model.logging.LogEntryRequest;
import pl.grupakpkpur.awslab.service.LoggingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("${rest.mapping.logging}")
public class LoggingController {

	private final LoggingService loggingService;

	@PostMapping("/log-entry")
	public void log(Authentication authentication, @RequestBody LogEntryRequest request) {
		User user = (User) authentication.getPrincipal();
		this.loggingService.logEntry(request, user);
	}
}
