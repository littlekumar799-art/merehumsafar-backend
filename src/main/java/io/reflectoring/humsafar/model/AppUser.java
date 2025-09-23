package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


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
    private String firstName;
    private String middleName;
    private  String lastName;
    private  String aboutMe;
    private  String fatherOccupation;
    private  String motherOccupation;
    private  String siblings;
    private  String religiousPractices;
    private  String hobbies;
    private  String isSmoke;
    private  String isDrink;
    private  String diet;
     private String isSubAdmin;
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
    private String liveWithFamily;

    private String password;

    @Transient
    private String confirmPassword;

    @ManyToOne
    @JoinColumn(name = "marital_status_type_id")
    private MaritalStatusType maritalStatus;

    @ManyToOne
    @JoinColumn(name = "mother_tongue_id")
    private MotherTongue motherTongue;

    @ManyToOne
    @JoinColumn(name = "caste_type_id")
    private CasteType caste;

    @ManyToOne
    @JoinColumn(name = "education_type_id")
    private EducationType highestEducation;

    @ManyToOne
    @JoinColumn(name = "occupation_type_id")
    private OccupationType occupation;

    @ManyToOne
    @JoinColumn(name = "employed_in_id")
    private EmployedIn employedIn;

    @ManyToOne
    @JoinColumn(name = "profile_for_id")
    private ProfileFor profileFor;
    @ManyToOne
     @JoinColumn(name = "uploaded_image_id")
    private UploadedImage uploadedImage;


    private String annualIncome;

    private String gender;
    @Column(name = "token") // Optional: to customize DB column name
    private String token;
    private String refreshToken;
    @Column(name = "created_at")
   private OffsetDateTime createdAt;

    public AppUser(String name, String email) {
        this.firstName = name;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}