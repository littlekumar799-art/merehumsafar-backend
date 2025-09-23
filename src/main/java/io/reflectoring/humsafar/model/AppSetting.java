package io.reflectoring.humsafar.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
public class AppSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appName;

    private String appLogo;

    private String email;
    private String contactNumber;
    private  String  twitterX;
    private  String instagram;
    private  String about;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


    // Foreign key relation with ThemeColor
    @ManyToOne
    @JoinColumn(name = "theme_color_id", referencedColumnName = "id")
    private ThemeColor themeColor;


    
}