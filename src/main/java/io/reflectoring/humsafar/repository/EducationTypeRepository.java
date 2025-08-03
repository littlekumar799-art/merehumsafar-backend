package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.EducationType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EducationTypeRepository extends JpaRepository<EducationType, Long> {
    boolean existsByName(String name);
}