package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.ThemeColor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ThemeColorRepository extends JpaRepository<ThemeColor, Long> {
    boolean existsByTitle(String title);
}
