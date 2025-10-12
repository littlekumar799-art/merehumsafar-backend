package io.reflectoring.humsafar.service;


import io.jsonwebtoken.Jwts;
import io.reflectoring.humsafar.dto.*;
import io.reflectoring.humsafar.model.OtpEntry;
import io.reflectoring.humsafar.model.AppUser;
import io.reflectoring.humsafar.model.ProfileFor;
import io.reflectoring.humsafar.provider.JwtProvider;
import io.reflectoring.humsafar.repository.OtpRepository;
import io.reflectoring.humsafar.repository.ProfileForRepository;
import io.reflectoring.humsafar.repository.UserRepository;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Random;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;



@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
private  final ProfileForRepository profileForRepository;

    public String signup(SignUpRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // ❌ No email sending if not needed
        return sendOtp(request.getEmail());
    }

    public String login(LoginRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            throw new RuntimeException("Email not registered");
        }
        return sendOtp(request.getEmail());
    }


//    public String verifyOtp(OtpRequest request) {
//        OtpEntry otpEntry = otpRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("OTP not found"));
//
//        if (otpEntry.isExpired()) {
//            otpRepository.deleteByEmail(request.getEmail());
//            throw new RuntimeException("OTP expired");
//        }
//
//        if (!otpEntry.getOtp().equals(request.getOtp())) {
//            throw new RuntimeException("Invalid OTP");
//        }
//
//        otpRepository.deleteByEmail(request.getEmail());
//        return jwtProvider.generateToken(request.getEmail());
//    }
public OtpVerifyResponse verifyOtp(OtpVerifyRequest request) {

    // 🔹 Step 1: Find OTP entry
    OtpEntry otpEntry = otpRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("OTP not found"));

    // 🔹 Step 2: Check OTP expiry
    if (otpEntry.isExpired()) {
        otpRepository.deleteByEmail(request.getEmail());
        throw new RuntimeException("OTP expired");
    }

    // 🔹 Step 3: Validate OTP match
    if (!otpEntry.getOtp().equals(request.getOtp())) {
        throw new RuntimeException("Invalid OTP");
    }

    // ✅ OTP is valid → Delete it after verification
    otpRepository.deleteByEmail(request.getEmail());

    // 🔹 Step 4: Generate JWT token
    String token = jwtProvider.generateToken(request.getEmail());

   Optional <AppUser> existingUser = userRepository.findByEmail(request.getEmail());

    AppUser user;

    if (existingUser.isPresent()) {
        // 🧠 Existing user → just update token
         user = existingUser.get();
    } else {
        // 🆕 New user → create and save
        ProfileFor profileFor = profileForRepository.findById(request.getProfileForId())
                .orElseThrow(() -> new RuntimeException("Invalid profileFor ID"));
        user = new AppUser();
        user.setEmail(request.getEmail());
        user.setProfileFor(profileFor);
        user.setLiveWithFamily("1");

    }
    user.setToken(token);
    userRepository.save(user);


    // ✅ Step 6: Return token + user
    return new OtpVerifyResponse(token, user);
}





    public String sendOtp(String email) {
        String otp = String.valueOf(new Random().nextInt(899999) + 100000);
        otpRepository.deleteByEmail(email);
        otpRepository.save(new OtpEntry(null, email, otp,   OffsetDateTime.now(),
                OffsetDateTime  .now()));
//        emailService.sendOtp(email, otp);
        return otp;
    }

    public boolean validateToken(String token) {
        try {
            // Step 1: Validate token structure and expiration
            String email = jwtProvider.getEmailFromToken(token); // Will throw if invalid/expired

            // Step 2: Check if the token is stored in DB
            Optional<AppUser> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                AppUser user = userOptional.get();
                return token.equals(user.getToken()); // Check if token matches DB token
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }


}