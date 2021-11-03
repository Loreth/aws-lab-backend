package pl.grupakpkpur.awslab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grupakpkpur.awslab.model.authentication.User;
import pl.grupakpkpur.awslab.model.logging.LogEntry;
import pl.grupakpkpur.awslab.model.logging.LogEntryRequest;
import pl.grupakpkpur.awslab.model.logging.LogEntryResponse;
import pl.grupakpkpur.awslab.repository.LoggingRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class LoggingService {

    private final LoggingRepository loggingRepository;

    public void logEntry(LogEntryRequest request, User user) {
        loggingRepository.save(LogEntry.fromRequest(request, user.getUsername()));
    }

    public List<LogEntryResponse> getLogs() {
        return StreamSupport
                .stream(loggingRepository.findAll().spliterator(), false)
                .map(logEntry -> new LogEntryResponse(logEntry.getDate(), logEntry.getIp(), logEntry.getUrl(), logEntry.getMethod(), logEntry.getUsername()))
                .collect(Collectors.toList());
    }


    public List<LogEntryResponse> getLogsByUsername(String username) {
        return loggingRepository.findAllByUsername(username).stream()
                .map(logEntry -> new LogEntryResponse(logEntry.getDate(), logEntry.getIp(), logEntry.getUrl(), logEntry.getMethod(), logEntry.getUsername()))
                .collect(Collectors.toList());
    }

    public List<LogEntryResponse> getLogsByDate(Date start, Date end) {
        return loggingRepository.findAllByDateBetween(start, end).stream()
                .map(logEntry -> new LogEntryResponse(logEntry.getDate(), logEntry.getIp(), logEntry.getUrl(), logEntry.getMethod(), logEntry.getUsername()))
                .collect(Collectors.toList());
    }

    public List<LogEntryResponse> getLogsByDateAndUsername(Date start, Date end, String username) {
        return loggingRepository.findAllByDateBetweenAndUsername(start, end, username).stream()
                .map(logEntry -> new LogEntryResponse(logEntry.getDate(), logEntry.getIp(), logEntry.getUrl(), logEntry.getMethod(), logEntry.getUsername()))
                .collect(Collectors.toList());
    }
}
