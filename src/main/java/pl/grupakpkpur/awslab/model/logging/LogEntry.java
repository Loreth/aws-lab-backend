package pl.grupakpkpur.awslab.model.logging;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDBTable(tableName = "LogEntry")
@Data
public class LogEntry {

	public static LogEntry fromRequest(LogEntryRequest request) {
		LogEntry entry = new LogEntry();
		entry.setId(UUID.randomUUID());
		entry.setDate(request.date());
		entry.setIp(request.ip());
		entry.setUrl(request.url());
		entry.setMethod(request.method());
		entry.setBody(request.body());
		entry.setUserId(request.userId());
		entry.setUserName(request.userName());

		return entry;
	}

	@DynamoDBHashKey private UUID id;
	private LocalDateTime date;
	private String ip;
	private String url;
	private String method;
	private Object body;
	private Long userId;
	private String userName;
}
