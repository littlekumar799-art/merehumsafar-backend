package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.UploadedImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedImageRepository extends JpaRepository<UploadedImage, String> {
}
