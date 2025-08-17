package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.MaritalStatusType;

import io.reflectoring.humsafar.repository.MaritalStatusTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/marital")
public class MaritalStatusTypeController {

    private final MaritalStatusTypeRepository maritalStatusRepo;

    // constructor injection
    public MaritalStatusTypeController(MaritalStatusTypeRepository maritalStatusRepo) {
        this.maritalStatusRepo = maritalStatusRepo;
    }

    // 🔹 1. Get all employedIn types
    @GetMapping
    public List<MaritalStatusType> getMaritalStatus() {
        return maritalStatusRepo.findAll();
    }

    // 🔹 2. Get Marital Status type by id
    @GetMapping("/{id}")
    public ResponseEntity<MaritalStatusType> getMaritalStatusById(@PathVariable Long id) {
        Optional<MaritalStatusType> maritalStatus = maritalStatusRepo.findById(id);
        return maritalStatus.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Create new Marital Status type
    @PostMapping
    public ResponseEntity<MaritalStatusType> createMaritalStatus(@RequestBody MaritalStatusType educationType) {
        MaritalStatusType savedMaritalStatus = maritalStatusRepo.save(educationType);
        return ResponseEntity.ok(savedMaritalStatus);
    }

    // 🔹 4. Update  Marital Status  type
    @PutMapping("/{id}")
    public ResponseEntity<MaritalStatusType> updateCaste(@PathVariable Long id, @RequestBody MaritalStatusType maritalStatus) {
        return maritalStatusRepo.findById(id)
                .map(edu -> {
                    edu.setName(maritalStatus.getName()); // मान लो CasteType में 'name' field है
                    MaritalStatusType updated = maritalStatusRepo.save(edu);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 5. Delete Marital Status type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaste(@PathVariable Long id) {
        return maritalStatusRepo.findById(id)
                .map(edu -> {
                    maritalStatusRepo.delete(edu);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
