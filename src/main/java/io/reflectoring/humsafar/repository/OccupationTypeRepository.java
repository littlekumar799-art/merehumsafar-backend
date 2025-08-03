package io.reflectoring.humsafar.repository;


import io.reflectoring.humsafar.model.EducationType;
import io.reflectoring.humsafar.model.OccupationType;

import org.springframework.data.jpa.repository.JpaRepository;
public interface OccupationTypeRepository extends JpaRepository<OccupationType, Long> {
    boolean existsByName(String name);
}