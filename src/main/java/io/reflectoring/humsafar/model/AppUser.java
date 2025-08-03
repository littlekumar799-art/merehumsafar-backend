package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private LocalDate dateOfBirth;

    private Integer height;

    private String country;
    private String state;
    private String city;
    private boolean liveWithFamily;

    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "marital_status_type")
    private String maritalStatus;

    private String motherTongue;

    @Column(name = "caste_type")
    private String caste;

    @Column(name = "education_type")
    private String highestEducation;

    @Column(name = "occupation_type")
    private String occupation;

    @Column(name = "employed_in")
    private String employedIn;

    @ManyToOne
    @JoinColumn(name = "profile_for_id")
    private ProfileFor profileFor;


    private String profileImageUrl;

    private String annualIncome;

    private String gender;
    @Column(name = "token") // Optional: to customize DB column name
    private String token;
    private String refreshToken;


    public AppUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}