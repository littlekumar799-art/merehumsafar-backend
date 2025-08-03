package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.MaritalStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaritalStatusTypeRepository extends JpaRepository<MaritalStatusType, Long> {
    boolean existsByName(String name);
}