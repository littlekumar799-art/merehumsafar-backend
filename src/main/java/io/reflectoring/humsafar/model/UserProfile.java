package io.reflectoring.humsafar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private Integer height;
    private  Integer weight;
    private Long maritalStatusId;
    private Long casteId;
    private Long highestEducationId;
    private Long occupationId;
    private Long employedInId;
    private String motherTongue;
    private Long profileForId;
    private String annualIncome;
    private  String country;
    private String state;
    private String city;
    private boolean liveWithFamily;
    private Long profileImageId;
    private String gender;

}
