package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.MotherTongue;
import io.reflectoring.humsafar.repository.MotherTongueRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/language")
public class MotherTongueController {

    private final MotherTongueRepository motherTongueRepository;

    // constructor injection
    public MotherTongueController(MotherTongueRepository motherTongueRepository) {
        this.motherTongueRepository = motherTongueRepository;
    }

    // 🔹 1. Get all education types
    @GetMapping
    public List<MotherTongue> getCasteTypes() {
        return motherTongueRepository.findAll();
    }

    // 🔹 2. Get education type by id
    @GetMapping("/{id}")
    public ResponseEntity<MotherTongue> getEducationById(@PathVariable Long id) {
        Optional<MotherTongue> education = motherTongueRepository.findById(id);
        return education.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Create new education type
    @PostMapping
    public ResponseEntity<MotherTongue> createEducation(@RequestBody MotherTongue motherTongue) {
        MotherTongue saved = motherTongueRepository.save(motherTongue);
        return ResponseEntity.ok(saved);
    }

    // 🔹 4. Update education type
    @PutMapping("/{id}")
    public ResponseEntity<MotherTongue> updateCaste(@PathVariable Long id, @RequestBody MotherTongue motherTongueDetails) {
        return motherTongueRepository.findById(id)
                .map(edu -> {
                    edu.setName(motherTongueDetails.getName()); // मान लो CasteType में 'name' field है
                    MotherTongue updated = motherTongueRepository.save(edu);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 5. Delete caste type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaste(@PathVariable Long id) {
        return motherTongueRepository.findById(id)
                .map(edu -> {
                    motherTongueRepository.delete(edu);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
