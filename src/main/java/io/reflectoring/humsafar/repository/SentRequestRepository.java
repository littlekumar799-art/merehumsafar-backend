package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.SentRequests;
import io.reflectoring.humsafar.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SentRequestRepository extends JpaRepository<SentRequests, Long> {
    Optional<SentRequests> findByFromUserId(Long fromUserId);
}
