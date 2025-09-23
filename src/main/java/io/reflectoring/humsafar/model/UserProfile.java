package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


}
