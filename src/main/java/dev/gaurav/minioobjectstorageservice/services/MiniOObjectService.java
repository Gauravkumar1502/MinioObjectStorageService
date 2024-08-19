package dev.gaurav.minioobjectstorageservice.services;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.UploadObjectArgs;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MiniOObjectService {
    private static final Logger logger = LoggerFactory.getLogger(MiniOObjectService.class);
    private final MinioClient minioClient;

    public MiniOObjectService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void uploadObject(String bucketName, MultipartFile file) {
        try {
            byte[] data = file.getBytes();
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(data);
            String checksum = new BigInteger(1, hash).toString(16);
            System.out.println("Uploading file with checksum: " + checksum);

            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(checksum)
                    .filename(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .tags(Map.of("name", file.getOriginalFilename()))
                    .build());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public Optional<List<Item>> listObjects(String bucketName) {
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
            List<Item> items = new LinkedList<>();
            for (Result<Item> result : results) {
                items.add(result.get());
                System.out.println(result.get().objectName());
            }
            return Optional.of(items);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }
}