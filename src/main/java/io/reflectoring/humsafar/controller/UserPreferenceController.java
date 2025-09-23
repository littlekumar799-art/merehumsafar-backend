package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.dto.UserPreferenceRequest;
import io.reflectoring.humsafar.model.*;
import io.reflectoring.humsafar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import java.util.List;
@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class UserPreferenceController {

    private final UserPreferenceRepository userPreferenceRepository;
    private final EducationTypeRepository educationTypeRepository;
    private final OccupationTypeRepository occupationTypeRepository;
    private final CasteTypeRepository casteTypeRepository;
    private final MaritalStatusTypeRepository maritalStatusTypeRepository;
    private final EmployedInRepository employedInRepository;
    private final UserRepository appUserRepository;

    // 🔹 CREATE
    @PostMapping("/create-user-preference")
    public ResponseEntity<UserPreference> createUserPreference(@RequestBody UserPreferenceRequest request) {
        UserPreference preference = mapRequestToUserPreference(request, new UserPreference());


        userPreferenceRepository.save(preference);
        return ResponseEntity.ok(preference);
    }

    // 🔹 READ All
    @GetMapping("/user-preferences")
    public ResponseEntity<List<UserPreference>> getAllUserPreferences() {
        return ResponseEntity.ok(userPreferenceRepository.findAll());
    }

    // 🔹 READ By ID
    @GetMapping("/user-preference/{id}")
    public ResponseEntity<UserPreference> getUserPreferenceById(@PathVariable Long id) {
        UserPreference preference = userPreferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserPreference not found with ID: " + id));
        return ResponseEntity.ok(preference);
    }

    // 🔹 UPDATE
    @PutMapping("/update-user-preference/{id}")
    public ResponseEntity<UserPreference> updateUserPreference(@PathVariable Long id, @RequestBody UserPreferenceRequest request) {
        UserPreference existing = userPreferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserPreference not found with ID: " + id));
        mapRequestToUserPreference(request, existing);

        userPreferenceRepository.save(existing);
        return ResponseEntity.ok(existing);
    }

    // 🔹 DELETE
    @DeleteMapping("/delete-user-preference/{id}")
    public ResponseEntity<String> deleteUserPreference(@PathVariable Long id) {
        UserPreference preference = userPreferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserPreference not found with ID: " + id));
        userPreferenceRepository.delete(preference);
        return ResponseEntity.ok("UserPreference deleted successfully.");
    }

    // 🔁 MAPPER FUNCTION
    private UserPreference mapRequestToUserPreference(UserPreferenceRequest request, UserPreference preference) {

        if (request.getPreferredAgeFrom() != null) preference.setPreferredAgeFrom(request.getPreferredAgeFrom());
        if (request.getPreferredAgeTo() != null) preference.setPreferredAgeTo(request.getPreferredAgeTo());
        if (request.getPreferredHeightFrom() != null) preference.setPreferredHeightFrom(request.getPreferredHeightFrom());
        if (request.getPreferredHeightTo() != null) preference.setPreferredHeightTo(request.getPreferredHeightTo());

        if(request.getAppUserId() != null) {
            AppUser user = appUserRepository.findById(request.getAppUserId())
                    .orElseThrow(() -> new RuntimeException("Invalid user ID"));
            preference.setAppUser(user);
        }

        if (request.getPreferredEducationTypeId() != null) {
            EducationType educationType = educationTypeRepository.findById(request.getPreferredEducationTypeId())
                    .orElseThrow(() -> new RuntimeException("Invalid education type ID"));
            preference.setPreferredEducation(educationType);
        }

        if (request.getPreferredOccupationTypeId() != null) {
            OccupationType occupationType = occupationTypeRepository.findById(request.getPreferredOccupationTypeId())
                    .orElseThrow(() -> new RuntimeException("Invalid occupation type ID"));
            preference.setPreferredOccupationType(occupationType);
        }

        if (request.getPreferredCasteTypeId() != null) {
            CasteType casteType = casteTypeRepository.findById(request.getPreferredCasteTypeId())
                    .orElseThrow(() -> new RuntimeException("Invalid caste type ID"));
            preference.setPreferredCaste(casteType);
        }

        if (request.getPreferredMaritalStatusTypeId() != null) {
            MaritalStatusType maritalStatus = maritalStatusTypeRepository.findById(request.getPreferredMaritalStatusTypeId())
                    .orElseThrow(() -> new RuntimeException("Invalid marital status type ID"));
            preference.setPreferredMaritalStatus(maritalStatus);
        }

        if (request.getPreferredEmployedInId() != null) {
            EmployedIn employedIn = employedInRepository.findById(request.getPreferredEmployedInId())
                    .orElseThrow(() -> new RuntimeException("Invalid employed in ID"));
            preference.setPreferredEmployedIn(employedIn);
        }

        return preference;
    }
}
