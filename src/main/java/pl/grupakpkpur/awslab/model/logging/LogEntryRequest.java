package pl.grupakpkpur.awslab.model.logging;

import java.util.Date;

public record LogEntryRequest(Date date, String ip, String url, String method, Object body, Long userId, String userName) {
}
