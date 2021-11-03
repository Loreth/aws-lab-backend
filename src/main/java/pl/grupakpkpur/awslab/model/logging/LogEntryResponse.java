package pl.grupakpkpur.awslab.model.logging;

import java.util.Date;

public record LogEntryResponse(Date date, String ip, String url, String method, String username) {
}
