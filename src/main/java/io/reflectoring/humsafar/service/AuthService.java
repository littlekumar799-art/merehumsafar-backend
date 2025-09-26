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

        // ✅ Generate 6-digit OTP
        String otp = String.valueOf(new Random().nextInt(899999) + 100000);

        // Fetch ProfileFor entity
        ProfileFor profileFor = profileForRepository.findById(request.getProfileForId())
                .orElseThrow(() -> new RuntimeException("Invalid profileFor ID"));

        AppUser user = new AppUser();
        user.setEmail(request.getEmail());
        user.setProfileFor(profileFor);
        user.setLiveWithFamily("1");

        // ✅ Check if an OTP entry already exists for this email
        OtpEntry otpEntry = otpRepository.findByEmail(request.getEmail())
                .orElse(new OtpEntry());  // create a new one if not present

        // Always update the same record
        otpEntry.setEmail(request.getEmail());
        otpEntry.setOtp(otp);
//        otpEntry.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // optional expiry
otpEntry.setCreatedAt(OffsetDateTime.now());
        // ✅ Save updated OTP first
        otpRepository.save(otpEntry);

        // ✅ Save the user
        userRepository.save(user);

        // ❌ No email sending if not needed
        return "OTP generated and saved successfully"; // or return the OTP if needed
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
        OtpEntry otpEntry = otpRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (otpEntry.isExpired()) {
            otpRepository.deleteByEmail(request.getEmail());
            throw new RuntimeException("OTP expired");
        }

        if (!otpEntry.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        otpRepository.deleteByEmail(request.getEmail());

        String token = jwtProvider.generateToken(request.getEmail());

        AppUser user = null;
        Optional<AppUser> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setToken(token);
            userRepository.save(user);
        }

        return new OtpVerifyResponse(token, user);
    }




    public String sendOtp(String email) {
        String otp = String.valueOf(new Random().nextInt(899999) + 100000);
        otpRepository.deleteByEmail(email);
        otpRepository.save(new OtpEntry(null, email, otp,   OffsetDateTime.now(),
                OffsetDateTime  .now()));
        emailService.sendOtp(email, otp);
        return "OTP sent to email";
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