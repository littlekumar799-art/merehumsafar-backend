package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.EducationType;
import io.reflectoring.humsafar.repository.EducationTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/education")
public class EducationTypeController {

    private final EducationTypeRepository educationRepo;

    // constructor injection
    public EducationTypeController(EducationTypeRepository eduRepo) {
        this.educationRepo = eduRepo;
    }

    // 🔹 1. Get all education types
    @GetMapping
    public List<EducationType> getCasteTypes() {
        return educationRepo.findAll();
    }

    // 🔹 2. Get education type by id
    @GetMapping("/{id}")
    public ResponseEntity<EducationType> getEducationById(@PathVariable Long id) {
        Optional<EducationType> education = educationRepo.findById(id);
        return education.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Create new education type
    @PostMapping
    public ResponseEntity<EducationType> createEducation(@RequestBody EducationType educationType) {
        EducationType savedEducation = educationRepo.save(educationType);
        return ResponseEntity.ok(savedEducation);
    }

    // 🔹 4. Update education type
    @PutMapping("/{id}")
    public ResponseEntity<EducationType> updateCaste(@PathVariable Long id, @RequestBody EducationType educationDetails) {
        return educationRepo.findById(id)
                .map(edu -> {
                    edu.setName(educationDetails.getName()); // मान लो CasteType में 'name' field है
                    EducationType updated = educationRepo.save(edu);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 5. Delete caste type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaste(@PathVariable Long id) {
        return educationRepo.findById(id)
                .map(edu -> {
                    educationRepo.delete(edu);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
