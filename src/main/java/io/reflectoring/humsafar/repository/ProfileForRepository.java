package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.MaritalStatusType;
import io.reflectoring.humsafar.model.ProfileFor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileForRepository extends JpaRepository<ProfileFor, Long> {
    boolean existsByName(String name);
}