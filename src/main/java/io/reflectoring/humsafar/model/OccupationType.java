package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OccupationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;



    // Constructors, Getters, Setters
    public OccupationType() {}
    public OccupationType(String name) { this.name = name; }
}

