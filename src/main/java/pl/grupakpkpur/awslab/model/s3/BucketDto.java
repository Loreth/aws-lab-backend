package pl.grupakpkpur.awslab.model.s3;

import java.time.Instant;

public record BucketDto(String name, Instant creationDate) {
}
