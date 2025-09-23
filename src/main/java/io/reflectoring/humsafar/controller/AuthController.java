package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.dto.*;
import io.reflectoring.humsafar.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/resend")
    public ResponseEntity<String> resend(@RequestBody String email) {
        return ResponseEntity.ok(authService.sendOtp(email));
    }

    @PostMapping("/verify")
    public ResponseEntity<OtpVerifyResponse> verifyOtp(@RequestBody OtpVerifyRequest request) {
        OtpVerifyResponse response = authService.verifyOtp(request);
        return ResponseEntity.ok(response);
    }

    //check wheter token is valid
    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validate(@RequestBody TokenRequest request) {
        boolean isValid = authService.validateToken(request.getToken());

        Map<String, Object> response = new HashMap<>();
        response.put("valid", isValid);

        if (isValid) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}