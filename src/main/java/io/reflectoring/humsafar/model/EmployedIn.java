package io.reflectoring.humsafar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EmployedIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    // Constructors, Getters, Setters
    public EmployedIn() {}
    public EmployedIn(String name) { this.name = name; }
}