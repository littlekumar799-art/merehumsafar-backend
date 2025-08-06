package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.UploadedImage;
import io.reflectoring.humsafar.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    Optional<UserPreference> findByAppUserId(Long appUserId);
}
