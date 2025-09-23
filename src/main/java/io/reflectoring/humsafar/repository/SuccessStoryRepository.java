package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.Banner;
import io.reflectoring.humsafar.model.SuccessStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuccessStoryRepository extends JpaRepository<SuccessStory, Long> {
    boolean existsById(Integer id);
}
