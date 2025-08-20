package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.Banner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    boolean existsByTitle(String title);
}
