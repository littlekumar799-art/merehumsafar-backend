package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.dto.CompleteProfileRequest;
import io.reflectoring.humsafar.dto.ResponseUpdateProfile;
import io.reflectoring.humsafar.model.AppUser;

import io.reflectoring.humsafar.model.ProfileFor;
import io.reflectoring.humsafar.repository.ProfileForRepository;
import io.reflectoring.humsafar.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor

public class RegisterController {

    private final UserRepository userRepository;
private  final ProfileForRepository profileForRepository;

@GetMapping("/profile/{email}")
public AppUser getUserProfile(@PathVariable String email) {
    return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
}
    @PutMapping("/complete-profile")
    public ResponseEntity<ResponseUpdateProfile> completeProfile(@RequestBody CompleteProfileRequest request, @RequestParam String email) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

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
        user.setProfileFor(profileFor);
        user.setProfileImageUrl(request.getProfileImageUrl());
        user.setAnnualIncome(request.getAnnualIncome());
        user.setGender(request.getGender());

        AppUser updatedUser = userRepository.save(user);

        // ✅ Custom response with status, message, and updated user
        ResponseUpdateProfile response = new ResponseUpdateProfile("Profile updated successfully", "200", updatedUser);

        return ResponseEntity.ok(response);
    }
}