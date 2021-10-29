package pl.grupakpkpur.awslab.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import pl.grupakpkpur.awslab.model.logging.LogEntry;

import java.util.UUID;

@EnableScan
public interface LoggingRepository extends CrudRepository<LogEntry, UUID> {
}
