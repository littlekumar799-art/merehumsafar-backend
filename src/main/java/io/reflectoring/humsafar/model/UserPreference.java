package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
    private String preferredAgeFrom;
    private String preferredAgeTo;
    private String preferredHeightFrom;
    private String preferredHeightTo;
    @ManyToOne
    @JoinColumn(name = "preferred_marital_status_type_id")
    private MaritalStatusType preferredMaritalStatus;
    private String preferredMotherTongue;
    @ManyToOne
    @JoinColumn(name = "preferred_caste_type_id")
    private CasteType preferredCaste;
    @ManyToOne
    @JoinColumn(name = "preferred_education_type_id")
    private EducationType preferredEducation;
    @ManyToOne
    @JoinColumn(name = "preferred_occupation_type_id")
    private OccupationType preferredOccupationType;
    @ManyToOne
    @JoinColumn(name = "preferred_employed_in_id")
    private EmployedIn preferredEmployedIn;

    private String preferredAnnualIncome;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
