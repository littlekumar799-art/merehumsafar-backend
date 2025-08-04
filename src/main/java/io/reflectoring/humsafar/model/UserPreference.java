package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    private String preferredAgeFrom;
    private String preferredAgeTo;
    private String preferredHeightFrom;
    private String preferredHeightTo;
    private String preferredMaritalStatus;
    private String preferredMotherTongue;
    private String preferredCaste;
    private String preferredHighestEducation;
    private String preferredOccupation;
    private String preferredEmployedIn;
}
