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
private  final MotherTongueRepository motherTongueRepository;
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

        // 🔁 Set only if not null
        if(request.getAboutMe() != null) user.setAboutMe(request.getAboutMe());
        if(request.getFatherOccupation()!= null) user.setFatherOccupation(request.getAboutMe());
        if(request.getMotherOccupation()!=null) user.setMotherOccupation(request.getMotherOccupation());
        if(request.getSiblings() !=null) user.setSiblings(request.getSiblings());
        if(request.getReligiousPractices()!=null) user.setReligiousPractices(request.getReligiousPractices());
        if(request.getHobbies() != null) user.setHobbies(request.getHobbies());
        if(request.getIsSmoke()!=null) user.setIsSmoke(request.getIsSmoke());
        if(request.getIsDrink()!=null) user.setIsDrink(request.getIsDrink());
        if(request.getDiet()!=null) user.setDiet(request.getDiet());

        /// /
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getMiddleName() != null) user.setMiddleName(request.getMiddleName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if (request.getDateOfBirth() != null) user.setDateOfBirth(request.getDateOfBirth());
        if (request.getHeight() != null) user.setHeight(request.getHeight());
        if (request.getCountry() != null) user.setCountry(request.getCountry());
        if (request.getState() != null) user.setState(request.getState());
        if (request.getCity() != null) user.setCity(request.getCity());
        if (request.getLiveWithFamily() != null) user.setLiveWithFamily(request.getLiveWithFamily());

        if (request.getAnnualIncome() != null) user.setAnnualIncome(request.getAnnualIncome());
        if (request.getGender() != null) user.setGender(request.getGender());

        // 🔁 Check and update relational entities only if ID is provided
        if (request.getProfileForId() != null) {
            ProfileFor profileFor = profileForRepository.findById(request.getProfileForId())
                    .orElseThrow(() -> new RuntimeException("Invalid profileFor ID"));
            user.setProfileFor(profileFor);
        }

        if(request.getMotherTongueId() !=null){
            MotherTongue motherTongue = motherTongueRepository.findById(request.getMotherTongueId())
                    .orElseThrow(() -> new RuntimeException("Invalid Mother Tongue ID"));
            user.setMotherTongue(motherTongue);
        }

        if (request.getEducationTypeId() != null) {
            EducationType educationType = educationTypeRepository.findById(request.getEducationTypeId())
                    .orElseThrow(() -> new RuntimeException("Invalid education type ID"));
            user.setHighestEducation(educationType);
        }

        if (request.getOccupationTypeId() != null) {
            OccupationType occupationType = occupationTypeRepository.findById(request.getOccupationTypeId())
                    .orElseThrow(() -> new RuntimeException("Invalid occupation type ID"));
            user.setOccupation(occupationType);
        }

        if (request.getCasteTypeId() != null) {
            CasteType casteType = casteTypeRepository.findById(request.getCasteTypeId())
                    .orElseThrow(() -> new RuntimeException("Invalid caste type ID"));
            user.setCaste(casteType);
        }

        if (request.getMaritalStatusTypeId() != null) {
            MaritalStatusType maritalStatusType = maritalStatusTypeRepository.findById(request.getMaritalStatusTypeId())
                    .orElseThrow(() -> new RuntimeException("Invalid marital status type ID"));
            user.setMaritalStatus(maritalStatusType);
        }

        if (request.getEmployedInId() != null) {
            EmployedIn employedIn = employedInRepository.findById(request.getEmployedInId())
                    .orElseThrow(() -> new RuntimeException("Invalid employed in ID"));
            user.setEmployedIn(employedIn);
        }

        if (request.getUploadedImageId() != null) {
            UploadedImage uploadedImage = uploadedImageRepository.findById(request.getUploadedImageId())
                    .orElseThrow(() -> new RuntimeException("Invalid uploaded image ID"));
            user.setUploadedImage(uploadedImage);
        }

        AppUser updatedUser = userRepository.save(user);
        ResponseUpdateProfile response = new ResponseUpdateProfile("Profile updated successfully", "200", updatedUser);
        return ResponseEntity.ok(response);
    }

}