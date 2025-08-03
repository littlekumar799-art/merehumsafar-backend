package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.dto.CompleteProfileRequest;
import io.reflectoring.humsafar.model.AppUser;

import io.reflectoring.humsafar.model.ProfileFor;
import io.reflectoring.humsafar.repository.ProfileForRepository;
import io.reflectoring.humsafar.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor

public class RegisterController {

    private final UserRepository userRepository;
private  final ProfileForRepository profileForRepository;
    @PutMapping("/complete-profile")
    public ResponseEntity<String> completeProfile(@RequestBody CompleteProfileRequest request, @RequestParam String email) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch ProfileFor entity


        ProfileFor profileFor = profileForRepository.findById(request.getProfileForId())
                .orElseThrow(() -> new RuntimeException("Invalid profileFor ID"));

        // Update user details
        user.setPhoneNumber(request.getPhoneNumber());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setHeight(request.getHeight());
        user.setName(request.getName());
        user.setCountry(request.getCountry());
        user.setState(request.getState());
        user.setCity(request.getCity());
        user.setLiveWithFamily(request.isLiveWithFamily());
        user.setMaritalStatus(request.getMaritalStatus());
        user.setMotherTongue(request.getMotherTongue());
        user.setCaste(request.getCaste());
        user.setHighestEducation(request.getHighestEducation());
        user.setOccupation(request.getOccupation());
        user.setEmployedIn(request.getEmployedIn());
        user.setProfileFor(profileFor); // ✅ Now correct
        user.setProfileImageUrl(request.getProfileImageUrl());
        user.setAnnualIncome(request.getAnnualIncome());
        user.setGender(request.getGender());

        userRepository.save(user);

        return ResponseEntity.ok("Profile updated successfully");
    }
}