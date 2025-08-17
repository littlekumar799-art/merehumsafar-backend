package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.CasteType;
import io.reflectoring.humsafar.repository.CasteTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/caste")
public class CasteTypeController {

    private final CasteTypeRepository casteRepo;

    // constructor injection
    public CasteTypeController(CasteTypeRepository casteRepo) {
        this.casteRepo = casteRepo;
    }

    // 🔹 1. Get all caste types
    @GetMapping
    public List<CasteType> getCasteTypes() {
        return casteRepo.findAll();
    }

    // 🔹 2. Get caste type by id
    @GetMapping("/{id}")
    public ResponseEntity<CasteType> getCasteById(@PathVariable Long id) {
        Optional<CasteType> caste = casteRepo.findById(id);
        return caste.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Create new caste type
    @PostMapping
    public ResponseEntity<CasteType> createCaste(@RequestBody CasteType casteType) {
        CasteType savedCaste = casteRepo.save(casteType);
        return ResponseEntity.ok(savedCaste);
    }

    // 🔹 4. Update caste type
    @PutMapping("/{id}")
    public ResponseEntity<CasteType> updateCaste(@PathVariable Long id, @RequestBody CasteType casteDetails) {
        return casteRepo.findById(id)
                .map(caste -> {
                    caste.setName(casteDetails.getName()); // मान लो CasteType में 'name' field है
                    CasteType updated = casteRepo.save(caste);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 5. Delete caste type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaste(@PathVariable Long id) {
        return casteRepo.findById(id)
                .map(caste -> {
                    casteRepo.delete(caste);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
