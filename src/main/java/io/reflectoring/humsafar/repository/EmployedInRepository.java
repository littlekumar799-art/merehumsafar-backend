package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.EducationType;
import org.springframework.data.jpa.repository.JpaRepository;

import io.reflectoring.humsafar.model.EmployedIn;
public interface EmployedInRepository extends JpaRepository<EmployedIn, Long> {
    boolean existsByName(String name);
}