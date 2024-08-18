package dev.gaurav.minioobjectstorageservice.models;

import io.minio.messages.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BucketDetails {
    private Bucket bucket;
    private SseConfiguration sseConfig;
    private LifecycleConfiguration lifecycleConfig;
    private NotificationConfiguration NotificationConfig;
    private String policy;
    private ReplicationConfiguration replicationConfig;
    private Tags tags;
    private VersioningConfiguration versioningConfig;
}
