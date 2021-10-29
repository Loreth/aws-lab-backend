package pl.grupakpkpur.awslab.model.logging;

import java.time.LocalDateTime;

public record LogEntryRequest(LocalDateTime date, String ip, String url, String method, Object body, Long userId, String userName) {
}
