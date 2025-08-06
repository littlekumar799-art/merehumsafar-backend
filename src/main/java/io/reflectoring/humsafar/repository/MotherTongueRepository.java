package io.reflectoring.humsafar.repository;
import io.reflectoring.humsafar.model.MotherTongue;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MotherTongueRepository extends JpaRepository<MotherTongue, Long> {
    boolean existsByName(String name);
}
