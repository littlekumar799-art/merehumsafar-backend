package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(unique = true, nullable = false)//
    private String image;
    @Column(unique = true, nullable = false)
    private String subTitle;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


    // ✅ Default constructor (JPA requires this)
    public Banner() {
    }

    // ✅ Parameterized constructor for seeding
    public Banner(String title, String image, String subTitle) {
        this.title = title;
        this.image = image;
        this.subTitle = subTitle;
    }

    // ✅ Getters & Setters
    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return title;
    }


}
