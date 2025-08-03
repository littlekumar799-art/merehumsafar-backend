package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProfileFor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;



    // Constructors, Getters, Setters

    public ProfileFor() {}
    public ProfileFor(String name) { this.name = name; }
}