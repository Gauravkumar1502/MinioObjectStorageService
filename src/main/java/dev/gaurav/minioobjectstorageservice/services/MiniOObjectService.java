package dev.gaurav.minioobjectstorageservice.services;

import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Mini0ObjectService {
    private static final Logger logger = LoggerFactory.getLogger(Mini0ObjectService.class);
    private final MinioClient minioClient;

    public Mini0ObjectService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void uploadObject(String bucketName, String objectName, String objectPath) {
        try {
            minioClient.uploadObject(bucketName, objectName, objectPath);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}