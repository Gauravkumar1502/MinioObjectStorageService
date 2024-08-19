package dev.gaurav.minioobjectstorageservice.contollers;

import dev.gaurav.minioobjectstorageservice.services.MiniOObjectService;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/minio/objects")
public class ObjectController {
    private static final Logger logger = LoggerFactory.getLogger(ObjectController.class);
    private final MiniOObjectService miniOObjectService;

    public ObjectController(MiniOObjectService miniOObjectService) {
        this.miniOObjectService = miniOObjectService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("<h1>Welcome to MiniO Object Storage Service Object Controller</h1>");
    }

    @PostMapping("/{bucketName}")
    public void uploadObject(@PathVariable String bucketName, @RequestParam("file") MultipartFile file) {
        miniOObjectService.uploadObject(bucketName, file);
    }

    @GetMapping("/{bucketName}")
    public ResponseEntity<List<Item>> listObjects(@PathVariable String bucketName) {
        return ResponseEntity.ok(miniOObjectService.listObjects(bucketName).orElse(null));
    }

}
