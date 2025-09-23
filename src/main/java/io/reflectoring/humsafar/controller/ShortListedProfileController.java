package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.dto.ShortListedProfileRequest;
import io.reflectoring.humsafar.model.AppUser;

import io.reflectoring.humsafar.model.ShortListedProfile;

import io.reflectoring.humsafar.repository.ShortListedProfileRepository;
import io.reflectoring.humsafar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class ShortListedProfileController {
    private  final ShortListedProfileRepository shortListedProfileRepository;

    private final UserRepository appUserRepository;

    // 🔹 CREATE
    @PostMapping("/add-shortlisted-profile")
    public ResponseEntity<ShortListedProfile> createShortListedProfile(@RequestBody ShortListedProfileRequest request) {
        ShortListedProfile shortlistedProfile = mapShortListedProfileRequest(request, new ShortListedProfile());

        shortListedProfileRepository.save(shortlistedProfile);
        return ResponseEntity.ok(shortlistedProfile);
    }

    // 🔹 READ All
    @GetMapping("/get-all-shortlisted-profiles")
    public ResponseEntity<List<ShortListedProfile>> getAllShortListedProfiles() {
        return ResponseEntity.ok(shortListedProfileRepository.findAll());
    }

    // 🔹 READ By ID
    @GetMapping("/get-shortlisted-profile/{id}")
    public ResponseEntity<ShortListedProfile> getMessageById(@PathVariable Long id) {
        ShortListedProfile shortListedProfile = shortListedProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shortlisted Profile not found with ID: " + id));
        return ResponseEntity.ok(shortListedProfile);
    }

    // 🔹 UPDATE
    @PutMapping("/update-shortlisted-profile/{id}")
    public ResponseEntity<ShortListedProfile> updateMessage(@PathVariable Long id, @RequestBody ShortListedProfileRequest request) {
        ShortListedProfile existing = shortListedProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shortlisted Profile not found with ID: " + id));
        mapShortListedProfileRequest(request, existing);

        shortListedProfileRepository.save(existing);
        return ResponseEntity.ok(existing);
    }

    // 🔹 DELETE
    @DeleteMapping("/delete-shortlisted-profile/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        ShortListedProfile shortlistedProfile = shortListedProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SentRequest not found with ID: " + id));
        shortListedProfileRepository.delete(shortlistedProfile);
        return ResponseEntity.ok("Shortlisted Profile deleted successfully.");
    }

    // 🔁 MAPPER FUNCTION
    private ShortListedProfile mapShortListedProfileRequest(ShortListedProfileRequest shortlistedProfile, ShortListedProfile request) {


        if(shortlistedProfile.getShortListedUserId() != null) {
            AppUser user = appUserRepository.findById(shortlistedProfile.getShortListedUserId())
                    .orElseThrow(() -> new RuntimeException("Invalid Shortlisted User ID"));
            request.setShortListedUser(user);
        }

        if(shortlistedProfile.getAppUserId() != null) {
            AppUser user = appUserRepository.findById(shortlistedProfile.getAppUserId())
                    .orElseThrow(() -> new RuntimeException("Invalid App User ID"));
            request.setUser(user);
        }

        return request;
    }
}

