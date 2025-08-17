package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.OccupationType;

import io.reflectoring.humsafar.model.ProfileFor;
import io.reflectoring.humsafar.repository.OccupationTypeRepository;
import io.reflectoring.humsafar.repository.ProfileForRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/profile-for")
public class ProfileForController {

    private final ProfileForRepository profileForRepo;

    // constructor injection
    public ProfileForController(ProfileForRepository profileForRepo) {
        this.profileForRepo = profileForRepo;
    }

    // 🔹 1. Get all Profile For
    @GetMapping
    public List<ProfileFor> getOccupation() {
        return profileForRepo.findAll();
    }

    // 🔹 2. Get Profile For by id
    @GetMapping("/{id}")
    public ResponseEntity<ProfileFor> getProfileForById(@PathVariable Long id) {
        Optional<ProfileFor> find = profileForRepo.findById(id);
        return find.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Create new Profile For
    @PostMapping
    public ResponseEntity<ProfileFor> createProfileFor(@RequestBody ProfileFor motherTongue) {
        ProfileFor saved = profileForRepo.save(motherTongue);
        return ResponseEntity.ok(saved);
    }

    // 🔹 4. Update Profile For
    @PutMapping("/{id}")
    public ResponseEntity<ProfileFor> updateProfileFor(@PathVariable Long id, @RequestBody ProfileFor motherTongueDetails) {
        return profileForRepo.findById(id)
                .map(edu -> {
                    edu.setName(motherTongueDetails.getName()); // मान लो CasteType में 'name' field है
                    ProfileFor updated = profileForRepo.save(edu);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 5. Delete Profile For
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileFor(@PathVariable Long id) {
        return profileForRepo.findById(id)
                .map(edu -> {
                    profileForRepo.delete(edu);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
