package pl.grupakpkpur.awslab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.authentication.User;
import pl.grupakpkpur.awslab.model.logging.LogEntry;
import pl.grupakpkpur.awslab.model.logging.LogEntryRequest;
import pl.grupakpkpur.awslab.repository.LoggingRepository;

@Service
@AllArgsConstructor
public class LoggingService {

	private final LoggingRepository loggingRepository;

	public void logEntry(LogEntryRequest request, User user) {
		loggingRepository.save(LogEntry.fromRequest(request, user.getUsername()));
	}
}
