package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CasteType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;



    // Constructors, Getters, Setters
    public CasteType() {}
    public CasteType(String name) { this.name = name; }
}
