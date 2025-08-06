package io.reflectoring.humsafar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompleteProfileRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Integer height;
    private String country;
    private String state;
    private String city;
    private String liveWithFamily;
    private Long maritalStatusTypeId;
    private String motherTongue;
    private Long casteTypeId;
    private Long educationTypeId;
    private Long occupationTypeId;
    private Long employedInId;
    private Long profileForId; // <-- change from entity to ID
    private Long uploadedImageId;
    private String annualIncome;
    private String gender;
}
