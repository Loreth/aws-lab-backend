package pl.grupakpkpur.awslab.service;

import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.logging.LogEntryRequest;

@Service
public class LoggingService {
	public void logEntry(LogEntryRequest request) {
		System.out.println(request);
	}
}
