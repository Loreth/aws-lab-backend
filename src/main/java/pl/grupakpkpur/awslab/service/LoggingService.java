package pl.grupakpkpur.awslab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.logging.LogEntry;
import pl.grupakpkpur.awslab.model.logging.LogEntryRequest;
import pl.grupakpkpur.awslab.repository.LoggingRepository;

@Service
@AllArgsConstructor
public class LoggingService {

	private final LoggingRepository loggingRepository;

	public void logEntry(LogEntryRequest request) {
		System.out.println(request);
		loggingRepository.save(LogEntry.fromRequest(request));
	}
}
