package pl.grupakpkpur.awslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.grupakpkpur.awslab.model.authentication.User;
import pl.grupakpkpur.awslab.model.logging.LogDatesRequest;
import pl.grupakpkpur.awslab.model.logging.LogEntryRequest;
import pl.grupakpkpur.awslab.model.logging.LogEntryResponse;
import pl.grupakpkpur.awslab.service.LoggingService;

import java.util.List;

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

    @PostMapping("/dates")
    public List<LogEntryResponse> getLogsByDate(@RequestBody LogDatesRequest dates) {
        return loggingService.getLogsByDate(dates.start(), dates.end());
    }

    @PostMapping("/dates/{username}")
    public List<LogEntryResponse> getLogsByDateAndUsername(@PathVariable String username, @RequestBody LogDatesRequest dates) {
        return loggingService.getLogsByDateAndUsername(dates.start(), dates.end(), username);
    }

    @GetMapping("/logs")
    public List<LogEntryResponse> getLogs() {
        return loggingService.getLogs();
    }

    @GetMapping("/user/{username}")
    public List<LogEntryResponse> getLogsByUsername(@PathVariable String username) {
        return loggingService.getLogsByUsername(username);
    }
}
