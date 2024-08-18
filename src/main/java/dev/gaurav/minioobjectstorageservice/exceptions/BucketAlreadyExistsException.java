package dev.gaurav.minioobjectstorageservice.exceptions;

public class BucketAlreadyExistsException extends RuntimeException {
    public BucketAlreadyExistsException(String bucketName) {
        super("Bucket '" + bucketName + "' already exists.");
    }
}
