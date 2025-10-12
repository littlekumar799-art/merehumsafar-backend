package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    boolean existsByTitle(String title);

    // 👇 Custom finder method
    List<ProfileImage> findByAppUserId(Integer appUserId);
}
