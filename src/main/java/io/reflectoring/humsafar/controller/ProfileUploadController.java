package io.reflectoring.humsafar.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.reflectoring.humsafar.service.ImageService;
import io.reflectoring.humsafar.model.UploadedImage;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

// Add CORS here
@RestController
@RequestMapping("/api/master/profile")
public class ProfileUploadController {

    private final ImageService imageService;

    public ProfileUploadController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadedImage> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            UploadedImage savedImage = imageService.saveImage(image);


            return ResponseEntity.ok(savedImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}