package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.Banner;
import io.reflectoring.humsafar.model.CasteType;
import io.reflectoring.humsafar.repository.BannerRepository;
import io.reflectoring.humsafar.repository.CasteTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/master/banner")
public class BannerController {

    private final BannerRepository bannerRepo;

    // constructor injection
    public BannerController(BannerRepository bannerRepo) {
        this.bannerRepo = bannerRepo;
    }

    // 🔹 1. Get all banner types
    @GetMapping
    public List<Banner> getBanner() {
        return bannerRepo.findAll();
    }

    // 🔹 2. Get banner by id
    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable Long id) {
        Optional<Banner> banner = bannerRepo.findById(id);
        return banner.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 3. Create new banner
    @PostMapping
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) {
        Banner saved= bannerRepo.save(banner);
        return ResponseEntity.ok(saved);
    }

    // 🔹 4. Update banner
    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBanner(
            @PathVariable Long id,
            @RequestBody Banner bannerDetails) {

        return bannerRepo.findById(id)
                .map(banner -> {
                    // DB से मिला हुआ banner का ID same रहेगा
                    banner.setImage(bannerDetails.getImage());
                    banner.setTitle(bannerDetails.getTitle());
                    banner.setSubTitle(bannerDetails.getSubTitle());

                    Banner updated = bannerRepo.save(banner);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // 🔹 5. Delete banner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        return bannerRepo.findById(id)
                .map(caste -> {
                    bannerRepo.delete(caste);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
