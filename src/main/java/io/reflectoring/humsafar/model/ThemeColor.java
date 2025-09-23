package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ThemeColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    @Column(nullable = false)
    private String primary200;
    @Column(nullable = false)
    private String primary400;
    @Column(nullable = false)
    private String primary600;
    @Column(nullable = false)
    private String primary800;
    @Column(nullable = false)
    private String secondary200;
    @Column(nullable = false)
    private String secondary400;
    @Column(nullable = false)
    private String secondary600;
    @Column(nullable = false)
    private String secondary800;
    @Column(nullable = false)
    private String secondaryDark200;
    @Column(nullable = false)
    private String secondaryDark400;
    @Column(nullable = false)
    private String secondaryDark600;
    @Column(nullable = false)
    private String secondaryDark800;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    public  ThemeColor (
            String title,
            String primary200,
            String primary400,
            String primary600,
            String primary800,
            String secondary200,
            String secondary400,
            String secondary600,
            String secondary800,
            String secondaryDark200,
            String secondaryDark400,
            String secondaryDark600,
            String secondaryDark800
    ) {
        this.title = title;
        this.primary200 = primary200;
        this.primary400 = primary400;
        this.primary600 = primary600;
        this.primary800 = primary800;
        this.secondary200= secondary200;
        this.secondary400 = secondary400;
        this.secondary600 = secondary600;
        this.secondary800 = secondary800;
        this.secondaryDark200 = secondaryDark200;
        this.secondaryDark400 = secondaryDark400;
        this.secondaryDark600 = secondaryDark600;
        this.secondaryDark800 = secondaryDark800;


    }

}
