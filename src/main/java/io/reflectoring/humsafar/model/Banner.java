package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(unique = true, nullable = false)// or title
    private String image;
    @Column(unique = true, nullable = false)
    private String subTitle;

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

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return subTitle;
    }

    public String setTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }



}
