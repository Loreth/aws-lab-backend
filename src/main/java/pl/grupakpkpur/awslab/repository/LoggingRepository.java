package pl.grupakpkpur.awslab.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import pl.grupakpkpur.awslab.model.logging.LogEntry;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@EnableScan
public interface LoggingRepository extends CrudRepository<LogEntry, UUID> {
    List<LogEntry> findAllByUsername(String username);
    List<LogEntry> findAllByDateBetween(Date start, Date end);
    List<LogEntry> findAllByDateBetweenAndUsername(Date start, Date end, String username);

}
