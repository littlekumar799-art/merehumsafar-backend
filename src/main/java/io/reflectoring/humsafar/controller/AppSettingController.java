package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.AppSetting;
import io.reflectoring.humsafar.service.AppSettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/master/app-setting")
public class AppSettingController {

    private final AppSettingService service;

    public AppSettingController(AppSettingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<AppSetting> get() {
        AppSetting setting = service.getSetting();
        return setting != null ? ResponseEntity.ok(setting) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AppSetting> saveOrUpdate(@RequestBody AppSetting appSetting) {
        return ResponseEntity.ok(service.saveOrUpdate(appSetting));
    }
}
