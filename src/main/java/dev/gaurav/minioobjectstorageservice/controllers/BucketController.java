package dev.gaurav.minioobjectstorageservice.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gaurav.minioobjectstorageservice.exceptions.BucketAlreadyExistsException;
import dev.gaurav.minioobjectstorageservice.exceptions.BucketNotFoundException;
import dev.gaurav.minioobjectstorageservice.models.Bucket;
import dev.gaurav.minioobjectstorageservice.services.MiniOBucketService;

@RestController
@RequestMapping("/api/v1/minio/buckets")
public class BucketController {
        private static final Logger logger = LoggerFactory.getLogger(BucketController.class);
        private final MiniOBucketService miniOBucketService;

        public BucketController(MiniOBucketService miniOBucketService) {
                this.miniOBucketService = miniOBucketService;
        }

        @GetMapping("/test")
        public ResponseEntity<String> test() {
                return ResponseEntity.ok("<h1>Welcome to MiniO Object Storage Service Bucket Controller</h1>");
        }

        @GetMapping("")
        public ResponseEntity<List<Bucket>> listBuckets() {
                return ResponseEntity.ok(miniOBucketService.listBuckets().orElse(null));
        }

        @GetMapping("/{bucketName}")
        public ResponseEntity<Bucket> getBucket(@PathVariable String bucketName) {
                return ResponseEntity.ok(miniOBucketService.getBucket(bucketName).orElse(null));
        }

        @GetMapping("/exists/{bucketName}")
        public ResponseEntity<String> isBucketExists(@PathVariable String bucketName) {
                try{
                        return ResponseEntity.ok(miniOBucketService.isBucketExists(bucketName) ?
                                "Bucket '" + bucketName + "' exists." : "Bucket '" + bucketName + "' does not exist.");
                } catch (BucketNotFoundException | IllegalArgumentException e) {
                        logger.warn(e.getMessage());
                        return ResponseEntity.badRequest().body(e.getMessage());
                } catch (Exception e) {
                        logger.error(e.getMessage());
                        return ResponseEntity.badRequest().body("Internal Server Error occurred.");
                }
        }

        @PostMapping("/{bucketName}")
        public ResponseEntity<String> createBucket(@PathVariable String bucketName) {
                try {
                        miniOBucketService.createBucket(bucketName);
                        return ResponseEntity.ok("Bucket '" + bucketName + "' created successfully.");
                } catch (BucketAlreadyExistsException | IllegalArgumentException e) {
                        logger.warn(e.getMessage());
                        return ResponseEntity.badRequest().body(e.getMessage());
                } catch (Exception e) {
                        logger.error(e.getMessage());
                        return ResponseEntity.badRequest().body("Internal Server Error occurred.");
                }
        }

        @DeleteMapping("/{bucketName}")
        public ResponseEntity<String> removeBucket(@PathVariable String bucketName) {
                try {
                        miniOBucketService.removeBucket(bucketName);
                        return ResponseEntity.ok("Bucket '" + bucketName + "' removed successfully.");
                } catch (BucketNotFoundException | IllegalArgumentException e) {
                        logger.warn(e.getMessage());
                        return ResponseEntity.badRequest().body(e.getMessage());
                } catch (Exception e) {
                        logger.error(e.getMessage());
                        return ResponseEntity.badRequest().body("Internal Server Error occurred.");
                }
        }



}
