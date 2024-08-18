package dev.gaurav.minioobjectstorageservice.exceptions;

public class BucketNotFoundException extends RuntimeException {
    public BucketNotFoundException(String bucketName) {
        super("Bucket '" + bucketName + "' not found.");
    }
}
