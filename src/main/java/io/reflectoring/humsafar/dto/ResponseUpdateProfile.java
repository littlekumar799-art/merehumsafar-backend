package io.reflectoring.humsafar.dto;

import io.reflectoring.humsafar.model.AppUser;

public class ResponseUpdateProfile {
    private String message;
    private String status;
    private AppUser data;

    // Constructor
    public ResponseUpdateProfile(String message, String status, AppUser data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppUser getData() {
        return data;
    }

    public void setData(AppUser data) {
        this.data = data;
    }
}