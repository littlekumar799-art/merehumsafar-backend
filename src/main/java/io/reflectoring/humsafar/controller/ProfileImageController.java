package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.ProfileImage;
import io.reflectoring.humsafar.repository.ProfileImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/profile-images")
public class ProfileImageController {

    private final ProfileImageRepository profileImageRepository;

    // Constructor injection
    public ProfileImageController(ProfileImageRepository profileImageRepository) {
        this.profileImageRepository = profileImageRepository;
    }

    // 🔹 1. Get all uploaded images
    @GetMapping
    public List<ProfileImage> getAllImages() {
        return profileImageRepository.findAll();
    }

    // 🔹 2. Get image by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfileImage> getImageById(@PathVariable Long id) {
        Optional<ProfileImage> find = profileImageRepository.findById(id);
        return find.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Get images by userId
    @GetMapping("/user/{userId}")
    public List<ProfileImage> getImagesByUserId(@PathVariable Integer userId) {
        return profileImageRepository.findByAppUserId(userId);
    }

    // 🔹 4. Create new profile image
    @PostMapping
    public ResponseEntity<ProfileImage> createImage(@RequestBody ProfileImage profileImage) {
        profileImage.setCreatedAt(OffsetDateTime.now());
        profileImage.setUpdatedAt(OffsetDateTime.now());
        ProfileImage saved = profileImageRepository.save(profileImage);
        return ResponseEntity.ok(saved);
    }

    // 🔹 5. Update existing profile image
    @PutMapping("/{id}")
    public ResponseEntity<ProfileImage> updateImage(@PathVariable Long id, @RequestBody ProfileImage newData) {
        return profileImageRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(newData.getTitle());
                    existing.setImageUrl(newData.getImageUrl());
                    existing.setIsPrimary(newData.getIsPrimary());
                    existing.setAppUserId(newData.getAppUserId());
                    existing.setUpdatedAt(OffsetDateTime.now());
                    ProfileImage updated = profileImageRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 6. Delete profile image
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        return profileImageRepository.findById(id)
                .map(existing -> {
                    profileImageRepository.delete(existing);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
