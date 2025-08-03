package io.reflectoring.humsafar.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompleteProfileRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Integer height;
    private String country;
    private String state;
    private String city;
    private boolean liveWithFamily;
    private String maritalStatus;
    private String motherTongue;
    private String caste;
    private String highestEducation;
    private String occupation;
    private String employedIn;
    private Long profileForId; // <-- change from entity to ID
    private String profileImageUrl;
    private String annualIncome;
    private String gender;


}
