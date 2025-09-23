package io.reflectoring.humsafar.controller;


import io.reflectoring.humsafar.model.ThemeColor;
import io.reflectoring.humsafar.repository.ThemeColorRepository;
import io.reflectoring.humsafar.service.ThemeColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master/theme-colors")
public class ThemeController {

    private final ThemeColorService service;

    public ThemeController(ThemeColorService service) {
        this.service = service;
    }

    @GetMapping
    public List<ThemeColor> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeColor> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ThemeColor> create(@RequestBody ThemeColor themeColor) {
        return ResponseEntity.ok(service.create(themeColor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThemeColor> update(@PathVariable Long id, @RequestBody ThemeColor themeColor) {
        return service.update(id, themeColor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
