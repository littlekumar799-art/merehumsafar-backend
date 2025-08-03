package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.CasteType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CasteTypeRepository extends JpaRepository<CasteType, Long> {
    boolean existsByName(String name);
}