package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MaritalStatusType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;



    // Constructors, Getters, Setters
    public MaritalStatusType() {}
    public MaritalStatusType(String name) { this.name = name; }
}