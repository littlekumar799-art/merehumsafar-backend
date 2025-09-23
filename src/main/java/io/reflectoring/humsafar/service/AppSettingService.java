package io.reflectoring.humsafar.service;


import io.reflectoring.humsafar.model.AppSetting;
import io.reflectoring.humsafar.repository.AppSettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AppSettingService {

    private final AppSettingRepository repository;

    public AppSettingService(AppSettingRepository repository) {
        this.repository = repository;
    }

    public AppSetting getSetting() {
        return repository.findAll().stream().findFirst().orElse(null);
    }

    public AppSetting saveOrUpdate(AppSetting appSetting) {
        AppSetting existing = getSetting();
        if (existing != null) {
            existing.setAppName(appSetting.getAppName());
            existing.setAppLogo(appSetting.getAppLogo());
            existing.setThemeColor(appSetting.getThemeColor());
            return repository.save(existing);
        } else {
            return repository.save(appSetting);
        }
    }
}