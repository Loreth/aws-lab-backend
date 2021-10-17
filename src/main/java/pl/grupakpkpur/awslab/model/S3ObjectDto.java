package pl.grupakpkpur.awslab.model;

import java.net.URL;
import java.time.Instant;

public record S3ObjectDto(String key, long sizeBytes, Instant lastModified, URL presignedUrl) {
}
