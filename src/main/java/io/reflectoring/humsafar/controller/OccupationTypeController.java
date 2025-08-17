package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.OccupationType;

import io.reflectoring.humsafar.repository.OccupationTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/occupation")
public class OccupationTypeController {

    private final OccupationTypeRepository occupationTypeRepo;

    // constructor injection
    public OccupationTypeController(OccupationTypeRepository occupationTypeRepo) {
        this.occupationTypeRepo = occupationTypeRepo;
    }

    // 🔹 1. Get all occupation types
    @GetMapping
    public List<OccupationType> getOccupation() {
        return occupationTypeRepo.findAll();
    }

    // 🔹 2. Get occupation type by id
    @GetMapping("/{id}")
    public ResponseEntity<OccupationType> getOccupationById(@PathVariable Long id) {
        Optional<OccupationType> education = occupationTypeRepo.findById(id);
        return education.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Create new occupation type
    @PostMapping
    public ResponseEntity<OccupationType> createOccupation(@RequestBody OccupationType motherTongue) {
        OccupationType saved = occupationTypeRepo.save(motherTongue);
        return ResponseEntity.ok(saved);
    }

    // 🔹 4. Update occupation type
    @PutMapping("/{id}")
    public ResponseEntity<OccupationType> updateOccupation(@PathVariable Long id, @RequestBody OccupationType motherTongueDetails) {
        return occupationTypeRepo.findById(id)
                .map(edu -> {
                    edu.setName(motherTongueDetails.getName()); // मान लो CasteType में 'name' field है
                    OccupationType updated = occupationTypeRepo.save(edu);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 5. Delete caste type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOccupation(@PathVariable Long id) {
        return occupationTypeRepo.findById(id)
                .map(edu -> {
                    occupationTypeRepo.delete(edu);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
