package io.reflectoring.humsafar.service;


import io.reflectoring.humsafar.model.ThemeColor;
import io.reflectoring.humsafar.repository.ThemeColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeColorService {
    private final ThemeColorRepository repository;

    public ThemeColorService(ThemeColorRepository repository) {
        this.repository = repository;
    }

    public List<ThemeColor> getAll() {
        return repository.findAll();
    }

    public Optional<ThemeColor> getById(Long id) {
        return repository.findById(id);
    }

    public ThemeColor create(ThemeColor themeColor) {
        if (repository.existsByTitle(themeColor.getTitle())) {
            throw new RuntimeException("Theme with title already exists!");
        }
        return repository.save(themeColor);
    }

    public Optional<ThemeColor> update(Long id, ThemeColor newData) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(newData.getTitle());
            existing.setPrimary200(newData.getPrimary200());
            existing.setPrimary400(newData.getPrimary400());
            existing.setPrimary600(newData.getPrimary600());
            existing.setPrimary800(newData.getPrimary800());

            existing.setSecondary200(newData.getSecondary200());
            existing.setSecondary400(newData.getSecondary400());
            existing.setSecondary600(newData.getSecondary600());
            existing.setSecondary800(newData.getSecondary800());

            existing.setSecondaryDark200(newData.getSecondaryDark200());
            existing.setSecondaryDark400(newData.getSecondaryDark400());
            existing.setSecondaryDark600(newData.getSecondaryDark600());
            existing.setSecondaryDark800(newData.getSecondaryDark800());

            return repository.save(existing);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
