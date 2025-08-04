package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime uploadedAt;

    public UploadedImage() {}

    public UploadedImage( String imageName, String imagePath, String mediaType) {

        this.imageName = imageName;
        this.imagePath = imagePath;
        this.mediaType = mediaType;
        this.uploadedAt = LocalDateTime.now();
    }

    // Getters and Setters
}
