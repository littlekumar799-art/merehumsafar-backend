package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class UploadedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;
    private String imagePath;
    private String mediaType;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


    public UploadedImage() {}

    public UploadedImage( String imageName, String imagePath, String mediaType) {

        this.imageName = imageName;
        this.imagePath = imagePath;
        this.mediaType = mediaType;
    }


    // Getters and Setters
}
