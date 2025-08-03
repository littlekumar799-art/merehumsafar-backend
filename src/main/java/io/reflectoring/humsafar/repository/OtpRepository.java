package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.OtpEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntry, Long> {
    Optional<OtpEntry> findByEmail(String email);
    void deleteByEmail(String email);
}
