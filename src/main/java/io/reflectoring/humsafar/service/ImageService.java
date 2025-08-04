package io.reflectoring.humsafar.service;


import io.reflectoring.humsafar.model.UploadedImage;
import io.reflectoring.humsafar.repository.UploadedImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Value;


import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ImageService {

    private final String uploadDir = "uploads/";

    private final UploadedImageRepository repository;

    @Value("${server.base-url:http://localhost:8080}")
    private String baseUrl;

    public ImageService(UploadedImageRepository repository) {
        this.repository = repository;
    }

    public UploadedImage saveImage(MultipartFile file) throws IOException {
        Files.createDirectories(Paths.get(uploadDir));

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        String storedFilename = UUID.randomUUID().toString() + extension;

        Path filePath = Paths.get(uploadDir, storedFilename);
        Files.write(filePath, file.getBytes());

        String imageUrl ="/uploads/" + storedFilename;
        String mediaType = file.getContentType();

        UploadedImage image = new UploadedImage(
                originalFilename,
                imageUrl,
                mediaType
        );
//first all data delete from table then save
    //    repository.deleteAll();



        return repository.save(image);
    }
}