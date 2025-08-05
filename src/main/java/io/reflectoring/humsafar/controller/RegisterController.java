package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.dto.CompleteProfileRequest;
import io.reflectoring.humsafar.dto.ResponseUpdateProfile;
import io.reflectoring.humsafar.model.*;

import io.reflectoring.humsafar.repository.*;


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
private final EducationTypeRepository educationTypeRepository;

private final OccupationTypeRepository occupationTypeRepository;
private final CasteTypeRepository casteTypeRepository;
private final MaritalStatusTypeRepository maritalStatusTypeRepository;
    private final EmployedInRepository employedInRepository;

    private final UploadedImageRepository uploadedImageRepository;



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

        EducationType educationType = educationTypeRepository.findById(request.getEducationTypeId())
                .orElseThrow(() -> new RuntimeException("Invalid education type ID"));

        OccupationType occupationType = occupationTypeRepository.findById(request.getOccupationTypeId())
                .orElseThrow(() -> new RuntimeException("Invalid occupation type ID"));

        CasteType casteType = casteTypeRepository.findById(request.getCasteTypeId())
                .orElseThrow(() -> new RuntimeException("Invalid caste type ID"));

        MaritalStatusType maritalStatusType = maritalStatusTypeRepository.findById(request.getMaritalStatusTypeId())
                .orElseThrow(() -> new RuntimeException("Invalid marital status type ID"));

        EmployedIn employedIn = employedInRepository.findById(request.getEmployedInId())
                .orElseThrow(() -> new RuntimeException("Invalid employed in ID"));

UploadedImage uploadedImage = uploadedImageRepository.findById(request.getUploadedImageId())
                .orElseThrow(() -> new RuntimeException("Invalid profile image ID"));



        // Update user details
        user.setPhoneNumber(request.getPhoneNumber());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setHeight(request.getHeight());
        user.setName(request.getName());
        user.setCountry(request.getCountry());
        user.setState(request.getState());
        user.setCity(request.getCity());
        user.setLiveWithFamily(request.isLiveWithFamily());
        user.setMaritalStatus(maritalStatusType);
        user.setMotherTongue(request.getMotherTongue());
        user.setCaste(casteType);
        user.setHighestEducation(educationType);
        user.setOccupation(occupationType);
        user.setEmployedIn(employedIn);
        user.setProfileFor(profileFor);
        user.setUploadedImage(uploadedImage);
        user.setAnnualIncome(request.getAnnualIncome());
        user.setGender(request.getGender());

        AppUser updatedUser = userRepository.save(user);

        // ✅ Custom response with status, message, and updated user
        ResponseUpdateProfile response = new ResponseUpdateProfile("Profile updated successfully", "200", updatedUser);

        return ResponseEntity.ok(response);
    }
}