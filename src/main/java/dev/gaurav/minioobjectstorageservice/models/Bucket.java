package dev.gaurav.minioobjectstorageservice.models;

import io.minio.messages.ResponseDate;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
public class Bucket {
    @Getter
    private String name;

    private ResponseDate creationDate;

    public Bucket() {
    }
    public Bucket(String name, ResponseDate creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public Bucket(io.minio.messages.Bucket bucket) {
        this.name = bucket.name();
        this.creationDate = new ResponseDate(bucket.creationDate());
    }

    public ZonedDateTime getCreationDate()
    {
        return creationDate.zonedDateTime();
    }

}
