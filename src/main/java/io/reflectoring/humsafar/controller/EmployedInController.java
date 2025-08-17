package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.EmployedIn;

import io.reflectoring.humsafar.repository.EmployedInRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/employed-in")
public class EmployedInController {

    private final EmployedInRepository employedInRepo;

    // constructor injection
    public EmployedInController(EmployedInRepository employedInRepo) {
        this.employedInRepo = employedInRepo;
    }

    // 🔹 1. Get all employedIn types
    @GetMapping
    public List<EmployedIn> getEmployedIn() {
        return employedInRepo.findAll();
    }

    // 🔹 2. Get EmployedIn type by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployedIn> getEducationById(@PathVariable Long id) {
        Optional<EmployedIn> employedIn = employedInRepo.findById(id);
        return employedIn.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Create new EmployedIn type
    @PostMapping
    public ResponseEntity<EmployedIn> createEducation(@RequestBody EmployedIn educationType) {
        EmployedIn savedEmployedIn = employedInRepo.save(educationType);
        return ResponseEntity.ok(savedEmployedIn);
    }

    // 🔹 4. Update EmployedIn type
    @PutMapping("/{id}")
    public ResponseEntity<EmployedIn> updateCaste(@PathVariable Long id, @RequestBody EmployedIn employedIn) {
        return employedInRepo.findById(id)
                .map(edu -> {
                    edu.setName(employedIn.getName()); // मान लो CasteType में 'name' field है
                    EmployedIn updated = employedInRepo.save(edu);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 5. Delete employedIn type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaste(@PathVariable Long id) {
        return employedInRepo.findById(id)
                .map(edu -> {
                    employedInRepo.delete(edu);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
