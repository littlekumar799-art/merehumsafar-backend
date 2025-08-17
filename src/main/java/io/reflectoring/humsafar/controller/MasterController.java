package io.reflectoring.humsafar.controller;

import io.reflectoring.humsafar.model.*;

import io.reflectoring.humsafar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/master")
//@CrossOrigin(origins = "https://autoadda.netlify.app") // Add CORS here
@RequiredArgsConstructor
public class MasterController {

    private final EducationTypeRepository educationRepo;
    private final OccupationTypeRepository occupationRepo;
    private final CasteTypeRepository casteRepo;
    private final MaritalStatusTypeRepository maritalStatusRepo;
    private final ProfileForRepository profileForRepo;
private  final EmployedInRepository employedInRepo;
private  final MotherTongueRepository motherTongueRepo;
private  final UserRepository  userRepo;

    //
    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    //
//    @GetMapping("/occupation")
//    public List<OccupationType> getOccupationTypes() {
//        return occupationRepo.findAll();
//    }

    //
//    @GetMapping("/caste")
//    public List<CasteType> getCasteTypes() {
//        return casteRepo.findAll();
//    }

    //
//    @GetMapping("/marital")
//    public List<MaritalStatusType> getMaritalStatusTypes() {
//        return maritalStatusRepo.findAll();
//    }

    //

//    @GetMapping("/mother-tongue")
//    public List<MotherTongue> getMotherTongue() {
//        return motherTongueRepo.findAll();
//    }
//    @GetMapping("/profile-for")
//    public List<ProfileFor> getProfileForTypes() {
//        return profileForRepo.findAll();
//    }
//
//    @GetMapping("/employed-in")
//    public List<EmployedIn> getEmployedInTypes() {
//        return employedInRepo.findAll();
//    }



}
