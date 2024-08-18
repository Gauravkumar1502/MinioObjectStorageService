package dev.gaurav.minioobjectstorageservice.services;

import dev.gaurav.minioobjectstorageservice.exceptions.BucketAlreadyExistsException;
import dev.gaurav.minioobjectstorageservice.exceptions.BucketNotFoundException;
import dev.gaurav.minioobjectstorageservice.models.Bucket;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class MiniOService {
    private final MinioClient minioClient;

    public MiniOService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public boolean isBucketExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    public void createBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (isBucketExists(bucketName)) {
            throw new BucketAlreadyExistsException(bucketName);
        }
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
    }

    public void removeBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!isBucketExists(bucketName)) {
            throw new BucketNotFoundException(bucketName);
        }
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    public Optional<List<Bucket>> listBuckets() {
        try{
            return Optional.of(minioClient.listBuckets().stream().map(Bucket::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Bucket> getBucket(String bucketName) {
        try {
            return minioClient.listBuckets()
                    .stream()
                    .filter(bucket -> bucket.name().equals(bucketName))
                    .findFirst()
                    .map(Bucket::new);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void validateBucketName(String name) {
        final Pattern BUCKET_NAME_REGEX = Pattern.compile("^[a-z0-9][a-z0-9\\.\\-]{1,61}[a-z0-9]$");
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("bucket name '" + name + "' cannot be empty");
        } else if (name.length() < 3 || name.length() > 63) {
            throw new IllegalArgumentException("bucket name '" + name + "' must be of length 3 to 63");
        } else if (name.contains("..") || name.contains(".-") || name.contains("-.")) {
            throw new IllegalArgumentException("bucket name '" + name + "' cannot contain successive characters '..', '.-' and '-.'");
        } else if (!BUCKET_NAME_REGEX.matcher(name).find()) {
            throw new IllegalArgumentException("bucket name '" + name + "' does not follow Amazon S3 standards. For more information refer https://docs.aws.amazon.com/AmazonS3/latest/userguide/bucketnamingrules.html");
        }
    }
}
