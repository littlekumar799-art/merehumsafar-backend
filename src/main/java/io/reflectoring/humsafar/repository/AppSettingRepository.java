package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {
}
