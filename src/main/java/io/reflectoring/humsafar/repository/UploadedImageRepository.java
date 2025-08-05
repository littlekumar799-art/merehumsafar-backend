package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.ProfileFor;
import io.reflectoring.humsafar.model.UploadedImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UploadedImageRepository extends JpaRepository<UploadedImage, Long> {
   Optional<UploadedImage> findByImageName(String imageName);
}
