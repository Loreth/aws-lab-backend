package pl.grupakpkpur.awslab.model;

import java.time.Instant;

public record BucketDto(String name, Instant creationDate) {
}
