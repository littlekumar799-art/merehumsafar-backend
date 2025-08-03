package io.reflectoring.humsafar.dto;

import io.reflectoring.humsafar.model.AppUser;

public class OtpVerifyResponse {
    private String token;
    private AppUser user;

    public OtpVerifyResponse(String token, AppUser user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public AppUser getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
